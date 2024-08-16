package com.hr.nums;

import java.util.Arrays;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-31
 */
public class SortColors_75 {


    public void sortColorsV1(int[] nums) {

        // [0,0,2,1,1,2]
        int low = 0, high = nums.length - 1, idx = 0;
        while (idx < high) {
            if (nums[idx] == 0) {
                nums[idx++] = nums[low];
                nums[low++] = 0;
            } else if (nums[idx] == 2) {
                nums[idx] = nums[high];
                nums[high--] = 2;
            } else {
                ++idx;
            }
        }
    }

    // 1,0,2,1,1,2
    public void sortColors(int[] nums) {

        int low = 0, index = 0, high = nums.length - 1;

        while (index <= high) {
            if (nums[index] == 2) {
                nums[index] = nums[high];
                nums[high--] = 2;
            } else if (nums[index] == 0) {
                nums[index++] = nums[low];
                nums[low++] = 0;
            } else {
                ++index;
            }
        }
    }

    public static void main(String[] args) {

        int[] nums = {2,0,2,1,1,0};
        SortColors_75 instance = ReflectUtils.getInstance(SortColors_75.class);
        instance.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }
}
