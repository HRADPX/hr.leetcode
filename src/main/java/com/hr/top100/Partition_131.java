package com.hr.top100;

import java.util.ArrayList;
import java.util.List;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-05-28
 *
 * 分隔回文串
 */
public class Partition_131 {

    public List<List<String>> partition(String s) {
        List<List<String>> rs = new ArrayList<>();
        char[] chars = s.toCharArray();
        boolean[][] flag = new boolean[chars.length][chars.length];
        for (int i = 0; i < chars.length; i++) {
            for (int j = 0; j <= i; j++) {
                if (i - j >= 2) {
                    flag[j][i] = flag[j + 1][i - 1] && chars[i] == chars[j];
                } else if (i == j || chars[j] == chars[i]) {
                    flag[j][i] = true;
                }
            }
        }
        backtrace(chars, flag, rs, new ArrayList<>(), 0);
        return rs;
    }

    // aab
    private void backtrace(char[] chars, boolean[][] flag, List<List<String>> rs, List<String> list, int low) {

        if (low == chars.length) {
            rs.add(new ArrayList<>(list));
            return;
        }
        for (int i = low; i < chars.length; i++) {
            if (flag[low][i]) {
                list.add(new String(chars, low, i - low + 1));
                backtrace(chars, flag, rs, list, i + 1);
                list.remove(list.size() - 1);
            }
        }
    }

    public static void main(String[] args) {

        Partition_131 instance = ReflectUtils.getInstance(Partition_131.class);
        System.out.println(instance.partition("aab"));
    }
}
