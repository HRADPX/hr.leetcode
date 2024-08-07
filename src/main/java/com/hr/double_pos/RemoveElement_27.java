package com.hr.double_pos;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-14
 *
 * 移除数组中所有值等于 val 的数，返回新数组的长度
 * 需要原地修改
 *
 * [1,2,3,3,4] val = 3
 * --> [1,2,4]
 */
public class RemoveElement_27 {

    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) return 0;

        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val)
                nums[k++] = nums[i];
        }
        return k;
    }
}
