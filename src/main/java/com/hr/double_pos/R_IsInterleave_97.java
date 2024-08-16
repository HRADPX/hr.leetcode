package com.hr.double_pos;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-12
 */
public class R_IsInterleave_97 {

    public boolean isInterleave(String s1, String s2, String s3) {
        // 忽略边界条件
        if (s1.length() + s2.length() != s3.length()) return false;
        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];
        dp[0][0] = true;
        for (int i = 1; i < s1.length(); i++) {
            if (dp[0][i - 1] && s1.charAt(i - 1) == s3.charAt(i - 1))
                dp[0][i] = true;
        }
        for (int i = 1; i < s2.length(); i++) {
            if (dp[i - 1][0] && s2.charAt(i - 1) == s3.charAt(i - 1))
                dp[i][0] = true;
        }
        // "aabcc", "dbbca", "aadbbcbcac"
        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if(s3.charAt(i + j - 1) == s1.charAt(i - 1))
                    dp[i][j] = dp[i - 1][j];
                if(!dp[i][j] && s3.charAt(i + j - 1) == s2.charAt(j - 1))
                    dp[i][j] = dp[i][j - 1];
            }
        }
        return dp[s1.length()][s2.length()];
    }

    public boolean isInterleaveV1(String s1, String s2, String s3) {

        int m = s1.length(), n = s2.length(), l = s3.length();
        if(m + n != l) return false;
        if(m == 0) return s2.equals(s3);
        if(n == 0) return s1.equals(s3);
        boolean[] dp = new boolean[n + 1];
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        char[] ss = s3.toCharArray();

        dp[0] = true;
        // base case
        for (int i = 1; i <= n; i++) {
            if(chars2[i - 1] == ss[i - 1]) dp[i] = true;
            else break;
        }

        for (int i = 1; i <= m; i++) {
            dp[0] = dp[0] && chars1[i - 1] == ss[i - 1];
            for (int j = 1; j <= n; j++) {
                dp[j] = dp[j] && ss[i + j - 1] == chars1[i - 1];
                if(!dp[j] && ss[i + j - 1] == chars2[j - 1])
                    dp[j] = dp[j - 1];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {

        R_IsInterleave_97 instance = ReflectUtils.getInstance(R_IsInterleave_97.class);
        System.out.println(instance.isInterleave("aabcc", "dbbca", "aadbbcbcac"));
    }
}
