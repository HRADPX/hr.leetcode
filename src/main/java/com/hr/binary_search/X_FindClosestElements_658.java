package com.hr.binary_search;

import java.util.ArrayList;
import java.util.List;

import com.hr.utils.ReflectUtils;

/**
 * @author huangran <huangran@kuaishou.com>
 * Created on 2024-04-02
 */
public class X_FindClosestElements_658 {

    // [1,2,3,4,5], k = 4, x = 3  ==> [1,2,6,7]
    // 方法一：找到最接近 x 的值，然后向两边扩散，时间复杂度 O(max(logN + K))
    // 方法二：取反，从数组两边向中靠拢，找到与 x 相差较大的 n - k 个数，但是时间复杂度 O(N)
    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>(k);
        if (arr.length < k) return res;
        int idx = this.binarySearch(arr, x);  // idx -> [0, arr.length]
        if (idx < arr.length && arr[idx] != x) idx--;
        int low = Math.min(arr.length - 1, idx), high = Math.min(idx + 1, arr.length), count = 0;
        while (count < k && low >= 0 && high < arr.length) {
            if (Math.abs(arr[low] - x) <= arr[high]- x)
                low--;
            else
                high++;
            ++count;
        }
        while (count < k) {
            if (low < 0) high++;
            else low--;
            ++count;
        }
        for (int i = low + 1; i < high; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    public List<Integer> findClosestElements2(int[] arr, int k, int x) {

       int right = this.binarySearch2(arr, x);
       int left = right - 1;
       List<Integer> res = new ArrayList<>(k);
       while (k > 0) {
           if (left < 0) {
               right++;
           } else if (right >= arr.length) {
               left--;
           } else if (x - arr[left] <= arr[right] - x) {
               left--;
           } else {
               right++;
           }
           k--;
       }
        for (int i = left + 1; i < right; i++) {
            res.add(arr[i]);
        }
       return res;
    }

    /**
     * 二分查找，最左边界
     */
    public int binarySearch2(int[] nums, int target) {

        int low = 0, high = nums.length - 1;

        // 0 2 2 3 4
        while (low < high) {
            int mid = (low + high) >> 1;
            if (nums[mid] >= target) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }


    public static void main(String[] args) {

        int[] nums = {1,2,3,4,5};
        X_FindClosestElements_658 instance = ReflectUtils.getInstance(X_FindClosestElements_658.class);
        System.out.println(instance.findClosestElements2(nums, 4, -1));
    }

    // // [1,2,3,4,5], k = 4, x = 3  ==> [1,2,6,7]
    // 时间复杂度 O(log(N - K) + K)
    public List<Integer> findClosestElements_v1(int[] arr, int k, int x) {

        int low = 0, high = arr.length - k;
        while (low < high) {
            int mid = (low + high) >>> 1;
            if (x - arr[mid] > arr[mid + k] - x) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        List<Integer> res = new ArrayList<>(k);
        for (int i = low; i < low + k; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    private int binarySearch(int[] nums, int x) {
        int low = 0, high = nums.length - 1;
        while (low <= high) {
            int mid = (low + high) >> 1;
            if (nums[mid] == x) return mid;
            if (nums[mid] > x) high = mid - 1;
            else low = mid + 1;
        }
        return low;
    }
}
