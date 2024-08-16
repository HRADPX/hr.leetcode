package com.hr.binary_search;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-04-01
 *
 * 寻找峰值  下上下
 */
public class R_FindPeakElement_162 {

    // 1,2,2,3,5,6,4
    // 要求 O(logN) 时间复杂度
    public int findPeakElement(int[] nums) {

        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public int findPeakElement2(int[] nums) {

        int left = 0, right = nums.length - 1;
        // 1,2,4,6,5,6,4
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] arrays = {1,2,2,3,5,6,4};
        R_FindPeakElement_162 instance = ReflectUtils.getInstance(R_FindPeakElement_162.class);
        System.out.println(instance.findPeakElement2(arrays));
    }
}
