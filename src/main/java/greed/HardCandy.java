package greed;

import java.util.HashMap;
import java.util.Map;

/**
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * <p>
 * 你需要按照以下要求，给这些孩子分发糖果：
 * <p>
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 * <p>
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 * 第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/candy
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/11 08:12
 */
public class HardCandy {

	public static void main(String[] args) {
		int[] ratings = new int[]{1, 2, 87, 87, 87, 2, 1};
		System.out.println(candy(ratings));
	}

	public static int candy(int[] ratings) {
		Map<Integer, Integer> indexWithCandyCount = new HashMap<>();
		// 初始化糖果数
		for (int i = 0; i < ratings.length; i++) {
			indexWithCandyCount.put(i, 1);
		}

		// 跟右侧的比
		for (int i = 0; i < ratings.length - 1; i++) {
			// 右边的分数高 && 右边糖果数小于等于左边
			Integer leftCandyCount = indexWithCandyCount.get(i);
			Integer rightCandyCount = indexWithCandyCount.get(i + 1);
			if (ratings[i + 1] > ratings[i] && rightCandyCount <= leftCandyCount) {
				// 给右侧的小盆友多一个糖果
				indexWithCandyCount.put(i + 1, leftCandyCount + 1);
			}
		}

		// 跟左侧的比
		for (int i = ratings.length - 1; i > 0; i--) {
			// 左边的分数高 && 左边糖果数小于等于右边
			Integer rightCandyCount = indexWithCandyCount.get(i);
			Integer leftCandyCount = indexWithCandyCount.get(i - 1);
			if (ratings[i - 1] > ratings[i] && leftCandyCount <= rightCandyCount) {
				// 给左侧的小盆友多一个糖果
				indexWithCandyCount.put(i - 1, rightCandyCount + 1);
			}
		}

		return indexWithCandyCount.values().stream()
				.reduce(Integer::sum)
				.orElse(0);
	}
}
