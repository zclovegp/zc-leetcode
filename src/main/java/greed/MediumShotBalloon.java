package greed;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 🔥更容易并行堆积在头部🔥
 * <p>
 * 有一些球形气球贴在一堵用 XY 平面表示的墙面上。墙面上的气球记录在整数数组 points ，其中points[i] = [xstart, xend] 表示水平直径在 xstart 和 xend之间的气球。你不知道气球的确切 y 坐标。
 * <p>
 * 一支弓箭可以沿着 x 轴从不同点 完全垂直 地射出。在坐标 x 处射出一支箭，若有一个气球的直径的开始和结束坐标为 xstart，xend， 且满足  xstart ≤ x ≤ xend，则该气球会被 引爆 。可以射出的弓箭的数量 没有限制 。 弓箭一旦被射出之后，可以无限地前进。
 * <p>
 * 给你一个数组 points ，返回引爆所有气球所必须射出的 最小 弓箭数 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
 * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
 * 示例 2：
 * <p>
 * 输入：points = [[1,2],[3,4],[5,6],[7,8]]
 * 输出：4
 * 解释：每个气球需要射出一支箭，总共需要4支箭。
 * 示例 3：
 * <p>
 * 输入：points = [[1,2],[2,3],[3,4],[4,5]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * - 在x = 2处发射箭，击破气球[1,2]和[2,3]。
 * - 在x = 4处射出箭，击破气球[3,4]和[4,5]。
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= points.length <= 105
 * points[i].length == 2
 * -231 <= xstart < xend <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/14 17:17
 */
public class MediumShotBalloon {

	public static void main(String[] args) {
		int[][] points = new int[][]{{10, 16}, {2, 8}, {1, 6}, {7, 12}};
		System.out.println(findMinArrowShots(points));
	}

	public static int findMinArrowShots(int[][] points) {
		if (points.length == 1) {
			return 1;
		}

		// 以开头正序排序，开头越小，越容易一起射中
		List<int[]> sortedBalloons = Arrays.stream(points)
				.sorted(Comparator.comparing(x -> x[0]))
				.collect(Collectors.toList());

		List<int[]> togetherShotBalloonIndexes = new ArrayList<>();
		togetherShotBalloonIndexes.add(sortedBalloons.get(0));

		int totalArrow = 0;
		for (int i = 1; i < sortedBalloons.size(); i++) {

			int[] balloonStartWithEnd = sortedBalloons.get(i);

			int start = balloonStartWithEnd[0];
			int end = balloonStartWithEnd[1];

			// 可以一起射
			boolean together = togetherShotBalloonIndexes.stream().allMatch(togetherShotBalloonIndex -> start <= togetherShotBalloonIndex[1] && togetherShotBalloonIndex[0] <= end);
			if (together) {
				togetherShotBalloonIndexes.add(balloonStartWithEnd);
			}
			// 不能一起射
			else {
				totalArrow++;
				togetherShotBalloonIndexes.clear();
				togetherShotBalloonIndexes.add(balloonStartWithEnd);
			}
		}

		// 遍历完还有没有一起射出的元素
		if (togetherShotBalloonIndexes.size() != 0) {
			totalArrow++;
			togetherShotBalloonIndexes.clear();
		}

		return totalArrow;
	}
}
