package com.hr.nums;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-14
 */
public class NumArray_303 {

    int[] sum;

    public NumArray_303(int[] nums) {
        sum = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++)
            sum[i + 1] = sum[i] + nums[i];
    }

    public int sumRange(int left, int right) {
        return sum[right + 1] - sum[left];
    }
}
