package com.hr.permute_compose;

import java.util.ArrayList;
import java.util.List;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-22
 */
public class Permute_46 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        this.backtrace(res, nums, new ArrayList<>(), new boolean[nums.length]);
        return res;
    }

    private void backtrace(List<List<Integer>> res, int[] nums, List<Integer> list, boolean[] flag) {

        if (list.size() == nums.length) {
            res.add(new ArrayList<>(list));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flag[i]) continue;
            list.add(nums[i]);
            flag[i] = true;
            this.backtrace(res, nums, list, flag);
            list.remove(list.size() - 1);
            flag[i] = false;
        }
    }

    public List<Integer> permute(int[] nums, int k) {
        List<Integer> res = new ArrayList<>();
        this.backtrace(res, nums, 0, new boolean[nums.length], k);
        return res;
    }

    private void backtrace(List<Integer> res, int[] nums, int num, boolean[] flag, int k) {

        if (k == 0) {
            res.add(num);
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (flag[i]) continue;
            num = num * 10 + nums[i];
            flag[i] = true;
            this.backtrace(res, nums, num, flag, k - 1);
            num = (num - nums[i]) / 10;
            flag[i] = false;
        }
    }

    public static void main(String[] args) {

        Permute_46 instance = ReflectUtils.getInstance(Permute_46.class);
        List<Integer> permuteList = instance.permute(new int[]{1, 2, 3, 4}, 3);
        System.out.println(permuteList);
        System.out.println(permuteList.size());
    }
}
