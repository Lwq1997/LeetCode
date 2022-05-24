package com.lwq.greedyalgorithm;

import java.util.Arrays;

/**
 * 有一个会议室，每个会议室只能容纳一个会议，会议的时间是固定的，会议的时间段不能重叠。
 * 给定一个会议的开始时间和结束时间，求在这个时间段内能容纳的最大会议数。
 */
public class BastArrange {
    class program {
        int start;
        int end;

        public program(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static int bestArrange(program[] programs, int time) {
        int count = 0;
        //按会议的结束时间从小到大排序
        Arrays.sort(programs, (a, b) -> a.end - b.end);
        for (program program : programs) {
            if (time <= program.start) {
                //说明可以额容纳这个会议
                count++;
                time = program.end;
            }
        }
        return count;
    }
}
