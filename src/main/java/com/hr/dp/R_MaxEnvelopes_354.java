package com.hr.dp;

import java.util.Arrays;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-04-07
 *
 * 最长子序列的二维展开
 * 按照第一个元素升序排序，对第二个元素降序排序，然后找到第二个元素的最长递增子序列
 * {1,8},{2,3},{5,4},{5,2},{6,7},{6,4}
 */
public class R_MaxEnvelopes_354 {

    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes.length <= 1) return envelopes.length;

        Arrays.sort(envelopes, (a, b) -> a[0] - b[0] == 0 ? b[1] - a[1] : a[0] - b[0]);

        int[] envelope = new int[envelopes.length];
        int idx = 0; envelope[0] = envelopes[0][1];

        for (int i = 1; i < envelopes.length; i++) {
            int index = this.binarySearch(envelope, idx, envelopes[i][1]);
            if (index > idx) envelope[++idx] = envelopes[i][1];
            else envelope[index] = envelopes[i][1];
        }
        return ++idx;
    }


    private int binarySearch(int[] nums, int high, int target) {

        int low = 0;
        while (low <= high) {
            int mid = (low + high) >>> 1;

            if (nums[mid] == target) return mid;
            if (nums[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }

    public static void main(String[] args) {

        int[][] envelopes = {{2, 3}, {1, 8}, {5, 2}, {6, 4}, {5, 4}, {6, 7}};
        R_MaxEnvelopes_354 instance = ReflectUtils.getInstance(R_MaxEnvelopes_354.class);
        System.out.println(instance.maxEnvelopes(envelopes));
    }
}
