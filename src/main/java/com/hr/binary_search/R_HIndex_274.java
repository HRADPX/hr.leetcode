package com.hr.binary_search;

import com.hr.utils.ReflectUtils;

public class R_HIndex_274 {

    public int hIndex2(int[] citations) {

        int[] counts = new int[citations.length + 1];
        for (int citation : citations) {
            counts[Math.min(citations.length, citation)]++;
        }
        int total = 0;
        for (int i = citations.length; i >= 0; i--) {
            total += counts[i];
            if (total >= i) {
                return i;
            }
        }
        return 0;
    }


    public int hIndex(int[] citations) {
        if (citations == null || citations.length <= 0) {
            return 0;
        }
        int low = 0, high = citations.length;
        while (low < high) {
            // 但是这里为什么需要加 + 1（防止死循环，如[0], [0,1]）
            int mid = (low + high + 1) >>> 1;
            int count = this.counts(citations, mid);
            // 如果 count 大于 mid，mid 可能就是最终答案，所以这里是 low = mid
            if (count >= mid) {
                low = mid;
            } else {
                high = mid - 1;
            }
        }
        return high;
    }


    private int counts(int[] citations, int target) {

        int count = 0;
        for (int citation : citations) {
            if (citation >= target) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args){
        int[] citations = {3,0,6,1,5};
        System.out.println(ReflectUtils.getInstance(R_HIndex_274.class).hIndex(citations));
    }

}
