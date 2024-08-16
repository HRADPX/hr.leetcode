package com.hr.list;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-08
 *
 * 丑数 就是质因子只包含 2、3 和 5 的正整数。
 *
 * [1, 2, 3, 4, 5, 6, 8, 9, 10, 12, ...]
 *
 * 同类型题：313
 */
public class R_NthUglyNumber_264 {

    public int nthUglyNumber(int n) {

        int[] dp = new int[n + 1];
        int a, b, c; a = b = c = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            int res = Math.min(Math.min(dp[a] * 2, dp[b] * 3), dp[c] * 5);
            dp[i] = res;
            if (res == dp[a] * 2) a++;
            if (res == dp[b] * 3) b++;
            if (res == dp[c] * 5) c++;
        }
        return dp[n];
    }

    // 暴力解法，超时
    public int nthUglyNumberV1(int n) {
        if (n == 1) return 1;
        int count = 0;
        int num = 1;
        while (count != n) {
            if (this.isUglyNum(num++)) count++;
        }
        return num - 1;
    }

    public int nthUglyNumber2(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        int i, j, k; i = j = k = 1;
        for (int l = 2; l <= n; l++) {

            int res = Math.min(Math.min(dp[i] * 2, dp[j] * 3), dp[k] * 5);
            if (res == dp[i] * 2) i++;
            if (res == dp[j] * 2) j++;
            if (res == dp[k] * 2) k++;
            dp[l] = res;
        }
        return dp[n];
    }


    private boolean isUglyNum(int num) {
        while ((num % 2) == 0) num /= 2;
        while ((num % 3) == 0) num /= 3;
        while ((num % 5) == 0) num /= 5;
        return num == 1;
    }

    public static void main(String[] args) {

        System.out.println(ReflectUtils.getInstance(R_NthUglyNumber_264.class).nthUglyNumber(10));
    }
}
