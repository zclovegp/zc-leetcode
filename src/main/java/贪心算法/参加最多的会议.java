package 贪心算法;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author zhaochong on 2023/6/11 14:50
 */
public class 参加最多的会议 {

	public static void main(String[] args) {
		int[][] meetings = {{1, 5}, {1, 5}, {1, 5}, {2, 3}, {2, 3}};
		int maxNum = maxMeetingNum(meetings);
		System.out.println("最多可以参加 " + maxNum + " 个会议");
	}

	public static int maxMeetingNum(int[][] events) {
		Arrays.sort(events, (a, b) -> {
			if (a[1] != b[1]) {
				// 按照结束时间排序（越早结束越先参加）
				return a[1] - b[1];
			} else {
				// 如果结束时间相同，按开始时间排序（越早开始越先参加）
				return a[0] - b[0];
			}
		});

		int count = 0;
		List<Integer> hadScheduleDay = new ArrayList<>();
		for (int[] event : events) {

			for (int i = event[0]; i <= event[1]; i++) {

				if (!hadScheduleDay.contains(i)) {
					hadScheduleDay.add(i);
					count++;
					break;
				}
			}
		}

		return count;
	}

}
