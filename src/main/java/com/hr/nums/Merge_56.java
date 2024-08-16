package com.hr.nums;

import com.hr.utils.ArrayUtils;
import com.hr.utils.ReflectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Merge_56 {

    /**
     * intervals = [[1,3],[2,6],[8,10],[15,18]]
     * 输出：[[1,6],[8,10],[15,18]]
     */
    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0] != 0 ? o1[0] - o2[0] : o1[1] - o2[1]);
        List<int[]> res = new ArrayList<>();

        res.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            int[] front = res.get(res.size() - 1);
            int[] interval = intervals[i];

            if (front[1] < interval[0]) {
                res.add(interval);
            } else if (front[1] <= interval[1]) {
                front[1] = interval[1];
            }
        }
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        System.out.println(Arrays.deepToString(ans));
        return ans;
    }

    public static void main(String[] args){

        int[][] array =  ArrayUtils.parse2DoubleNums("[[1,3],[2,6],[8,10],[15,18]]");
        Merge_56 instance = ReflectUtils.getInstance(Merge_56.class);
        instance.merge(array);
    }

}
