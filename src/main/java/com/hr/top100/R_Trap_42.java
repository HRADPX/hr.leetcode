package com.hr.top100;

import java.util.Arrays;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-21
 */
public class R_Trap_42 {

    // 对于位置 i 最多能接水的值为 i 左右两侧的高于 i 的最大值中的较小值（Math.min(leftMax, rightMax)） - height[i]
    // 如 height[i] = 1, i 左边的最大值为 height[i - m] = 3, i 右边的最大值为 height[i + n] = 6, 那么 i 处最多能接 3 - 1 = 2 的水量
    // 所以本题相当于要求数组每个位置左右两边的最大值

    // 4,2,0,3,2,5
    public int trapDp(int[] height) {
        int[] left = new int[height.length];
        int[] right = new int[height.length];

        left[0] = height[0];
        right[height.length - 1] = height[height.length - 1];

        for (int i = 1; i < height.length; i++) {
            left[i] = Math.max(left[i - 1], height[i]);
        }

        // right 数组不要也可以，在遍历的过程中就可以直接计算 i 处的存水量
        for (int i = height.length - 2; i >= 0; i--) {
            right[i] = Math.max(right[i + 1], height[i]);
        }
        System.out.println(Arrays.toString(left));
        System.out.println(Arrays.toString(right));

        int res = 0;
        for (int i = 0; i < height.length; i++) {
            res += Math.min(left[i], right[i]) - height[i];
        }
        return res;

    }

    // 单调栈
    public int trap(int[] height) {
        if (height.length <= 2) return 0;
        
        int[] stack = new int[height.length];
        stack[0] = -1;
        int top = -1, res = 0;

        for (int i = 0; i < height.length; i++) {
            while (top != -1 && height[stack[top]] < height[i]) {
                int popIndex = stack[top--];
                if(top == -1) break; // avoid {0,1}
                int width = i - stack[top] - 1;
                res += width * (Math.min(height[i],height[stack[top]]) - height[popIndex]);
            }
            stack[++top] = i;
        }
        return res;
    }


    public int trap2(int[] nums) {

        if (nums == null || nums.length <= 2) return 0;
        int[] stack = new int[nums.length];
        int top = -1, sum = 0;

        for (int i = 0; i < nums.length; i++) {
            while (top != -1 && nums[stack[top]] < nums[i]) {
                int popIndex = stack[top--];
                if (top == -1) break;
                sum += (Math.min(nums[i], nums[stack[top]]) - nums[popIndex]) * (i - 1 - stack[top]);
            }
            stack[++top] = i;
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] height = {4,2,0,3,2,5};
//        int[] height = {0,1};
        R_Trap_42 instance = ReflectUtils.getInstance(R_Trap_42.class);
        System.out.println(instance.trap(height));
        System.out.println(instance.trap2(height));
    }
}
