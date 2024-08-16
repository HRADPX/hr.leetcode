package com.hr.nums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Insert_57 {

    /**
     * 输入：intervals = [[1,2],[3,5],[6,7],[8,10],[12,16]], newInterval = [4,8]
     * 输出：[[1,2],[3,10],[12,16]]
     */
    public int[][] insert(int[][] intervals, int[] newInterval) {

        if (intervals == null || intervals.length == 0) {
            return new int[][] {newInterval};
        }
        List<int[]> res = new ArrayList<>();
        res.add(newInterval);

        for (int[] interval : intervals) {

            int[] candidate = res.get(res.size() - 1);

            int min = Math.max(candidate[0], interval[0]);
            int max = Math.min(candidate[1], interval[1]);

            if (min <= max) {
                candidate[0] = Math.min(candidate[0], interval[0]);
                candidate[1] = Math.max(candidate[1], interval[1]);
            } else if (candidate[0] > interval[1]) {
                res.add(res.size() - 1, interval);
            } else {
                res.add(interval);
            }
        }
        int[][] ans = new int[res.size()][2];
        for (int i = 0; i < res.size(); i++) {
            ans[i] = res.get(i);
        }
        System.out.println(Arrays.deepToString(ans));
        return ans;
    }
}
