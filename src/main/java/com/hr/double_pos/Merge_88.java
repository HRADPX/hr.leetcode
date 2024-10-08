package com.hr.double_pos;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-03-12
 */
public class Merge_88 {

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n <= 0) return;
        int i = m - 1, j = n - 1, k = m + n - 1;

        while (i >= 0 && j >= 0) {

            if (nums1[i] > nums2[j])
                nums1[k--] = nums1[i--];
            else
                nums1[k--] = nums2[j--];
        }
        while (i >= 0) nums1[k--] = nums1[i--];
        while (j >= 0) nums1[k--] = nums1[j--];
    }
}
