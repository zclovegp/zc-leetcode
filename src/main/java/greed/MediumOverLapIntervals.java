package greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 🔥更容易插空🔥
 * <p>
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: intervals = [[1,2],[2,3],[3,4],[1,3]]
 * 输出: 1
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 * 示例 2:
 * <p>
 * 输入: intervals = [ [1,2], [1,2], [1,2] ]
 * 输出: 2
 * 解释: 你需要移除两个 [1,2] 来使剩下的区间没有重叠。
 * 示例 3:
 * <p>
 * 输入: intervals = [ [1,2], [2,3] ]
 * 输出: 0
 * 解释: 你不需要移除任何区间，因为它们已经是无重叠的了。
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= intervals.length <= 105
 * intervals[i].length == 2
 * -5 * 104 <= starti < endi <= 5 * 104
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/non-overlapping-intervals
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/13 09:11
 */
public class MediumOverLapIntervals {

	public static void main(String[] args) {
		int[][] intervals = new int[][]{{1, 2}, {2, 3}, {3, 4}, {1, 3}};
		System.out.println(eraseOverlapIntervals(intervals));
	}

	public static int eraseOverlapIntervals(int[][] intervals) {

		// 以结尾正序排序，结尾越小，越容易插空不容易重叠
		List<int[]> sortedIntervals = Arrays.stream(intervals)
				.sorted(Comparator.comparing(x -> x[1]))
				.collect(Collectors.toList());

		List<int[]> targetOverLaps = new ArrayList<>();
		for (int[] waitCheckInterval : sortedIntervals) {

			if (targetOverLaps.size() == 0) {
				targetOverLaps.add(waitCheckInterval);
				continue;
			}

			boolean allNotOverLap = true;
			for (int[] targetOverLap : targetOverLaps) {
				// 有交集
				if (targetOverLap[0] < waitCheckInterval[1] && targetOverLap[1] > waitCheckInterval[0]) {
					allNotOverLap = false;
					break;
				}
			}

			if (allNotOverLap) {
				targetOverLaps.add(waitCheckInterval);
			}
		}

		return sortedIntervals.size() - targetOverLaps.size();
	}
}
