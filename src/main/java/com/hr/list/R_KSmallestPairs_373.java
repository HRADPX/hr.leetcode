package com.hr.list;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-08
 * 这个解法现在过不了所有测试用例了，超时...
 *
 */
public class R_KSmallestPairs_373 {

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {

        // 大顶堆
        PriorityQueue<List<Integer>> queue = new PriorityQueue<>(k, (o1, o2) ->
                o2.stream().mapToInt(Integer::intValue).sum()
                        - o1.stream().mapToInt(Integer::intValue).sum()
        );
        for (int num1 : nums1) {
            for (int num2 : nums2) {
                List<Integer> list = new ArrayList<>();
                list.add(num1);
                list.add(num2);
                if (queue.size() < k) {
                    queue.add(list);
                } else {
                    if (queue.comparator().compare(list, queue.peek()) > 0) {
                        queue.remove();
                        queue.add(list);
                    }
                }
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        while (!queue.isEmpty()) {
            res.add(0, queue.remove());
        }
        return res;
    }

    public static void main(String[] args) {
        R_KSmallestPairs_373 instance = ReflectUtils.getInstance(R_KSmallestPairs_373.class);
        System.out.println(instance.kSmallestPairs(new int[] {1, 7, 11}, new int[] {2, 4, 6}, 5));

    }
}