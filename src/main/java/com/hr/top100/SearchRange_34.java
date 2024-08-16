package com.hr.top100;

import java.util.Arrays;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-28
 */
public class SearchRange_34 {

    public int[] searchRange(int[] nums, int target) {
        int left = this.binarySearch(nums, target - 0.5);
        int right = this.binarySearch(nums, target + 0.5);
        return left == right ? new int[] {-1, -1} : new int[] {left, right - 1};
    }

    private int binarySearch(int[] nums, double target) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] > target) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }


    public int searchRange2(int[] nums, int target, boolean lower) {

        // 5,7,7,8,8,10
        int low = 0, high = nums.length - 1, ans = nums.length;

        while (low <= high) {
            int mid = (low + high) / 2;
             if (nums[mid] > target || (lower && nums[mid] >= target)) {
                high = mid - 1;
                ans = mid;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,2,2,4,5};
        SearchRange_34 instance = ReflectUtils.getInstance(SearchRange_34.class);
        System.out.println(instance.searchRange2(nums, 2, true));
        System.out.println(instance.searchRange2(nums, 2, false));
        System.out.println(Arrays.toString(instance.searchRange(nums, 2)));
    }
}
