package com.hr.threadlocal;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ThreadLocal 在线程池中使用
 * 核心思路：
 *  公共内存的存储结构设计，必须是 ThreadLocal
 *  公共内存在线程间传递
 */
public class EnhanceThreadLocal {

    private static final DelegatingThreadLocal<String> logStr = new DelegatingThreadLocal<>();

    public static void main(String[] args) {

        ExecutorService executor = Executors.newFixedThreadPool(2);
        logStr.set("new ThreadLocal....");

        executor.execute(new DelegatingRunnable(() -> {
                logStr.set("bbbb");
                System.out.println("threadName:" + Thread.currentThread().getName() + ", value:" + logStr.get());
        }));
        executor.execute(new DelegatingRunnable(() ->
                System.out.println("threadName:" + Thread.currentThread().getName() + ", value:" + logStr.get()))
        );
    }

}


class DelegatingRunnable implements Runnable {

    private final Map<DelegatingThreadLocal<Object>, Object> holder;
    private final Runnable runnable;

    DelegatingRunnable(Runnable runnable) {
        // 初始化任务时，将主线程中的值 copy 到 Runner 中，但是没有放到工作线程中，这里只是保存
        holder = DelegatingThreadLocal.copyFrom();
        this.runnable = runnable;
    }

    @Override
    public void run() {
        // 执行 run 方法，这是线程池线程执行的，将上面保存的值复制到工作线程中
        DelegatingThreadLocal.copyTo(holder);
        runnable.run();
    }
}

@SuppressWarnings("unchecked")
class DelegatingThreadLocal<T> extends ThreadLocal<T> {

    private static ThreadLocal<Map<DelegatingThreadLocal<Object>, Object>> holder = ThreadLocal.withInitial(HashMap::new);


    @Override
    public void set(T value) {
        holder.get().put((DelegatingThreadLocal<Object>) this, value);
    }

    @Override
    public T get() {
        return (T) holder.get().get(this);
    }

    /**
     * 将公共存储里的值存储到工作线程的 threadLocal 中
     */
    public static void copyTo(Map<DelegatingThreadLocal<Object>, Object> holderFromRunnable) {
        // 将公共值拷贝到当前的工作线程
        Map<DelegatingThreadLocal<Object>, Object> holderFromThread = holder.get();
        if (holderFromThread == null || holderFromThread.isEmpty()) {
            holder.set(holderFromRunnable);
            return;
        }
        holderFromRunnable.forEach((k, v) -> {
            if (holderFromThread.containsKey(k)) {
                holderFromThread.put(k, v);
            }
        });
    }

    public static Map<DelegatingThreadLocal<Object>, Object> copyFrom() {
        Map<DelegatingThreadLocal<Object>, Object> contextMap = holder.get();
        Map<DelegatingThreadLocal<Object>, Object> snapshot = new HashMap<>();
        contextMap.forEach(snapshot::put);
        return snapshot;
    }
}