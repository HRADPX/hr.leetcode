package com.hr.nums;

import java.util.Arrays;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-08
 */
public class NthSuperUglyNumber_313 {

    // 思路同 264
    public int nthSuperUglyNumber(int n, int[] primes) {
        if (n == 1) return 1;
        Arrays.sort(primes);
        // 用 int[] 需要考虑溢出的场景...
        long[] dp = new long[n + 1];
        dp[1] = 1;

        int[] p = new int[primes.length];
        Arrays.fill(p, 1);

        for (int i = 2; i <= n; i++) {
            long res = Integer.MAX_VALUE;
            for (int j = 0; j < p.length; j++)
                res = Math.min(res, dp[p[j]] * primes[j]);

            for (int j = 0; j < p.length; j++) {
                if (res == dp[p[j]] * primes[j]) p[j]++;
            }
            dp[i] = res;
        }
        return (int) dp[n];
    }

    public static void main(String[] args) {

        NthSuperUglyNumber_313 instance = ReflectUtils.getInstance(NthSuperUglyNumber_313.class);
//        System.out.println(instance.nthSuperUglyNumber(12, new int[] {2, 7, 13, 19, 7, 13, 19}));
        System.out.println(instance.nthSuperUglyNumber(12, new int[] {2, 3, 5}));
    }
}
