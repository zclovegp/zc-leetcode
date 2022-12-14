package 动态规划;

/**
 * 413. 等差数列划分
 * 中等
 * 494
 * 相关企业
 * 如果一个数列 至少有三个元素 ，并且任意两个相邻元素之差相同，则称该数列为等差数列。
 * <p>
 * 例如，[1,3,5,7,9]、[7,7,7,7] 和 [3,-1,-5,-9] 都是等差数列。
 * 给你一个整数数组 nums ，返回数组 nums 中所有为等差数组的 子数组 个数。
 * <p>
 * 子数组 是数组中的一个连续序列。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,2,3,4]
 * 输出：3
 * 解释：nums 中有三个子等差数组：[1, 2, 3]、[2, 3, 4] 和 [1,2,3,4] 自身。
 * 示例 2：
 * <p>
 * 输入：nums = [1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -1000 <= nums[i] <= 1000
 *
 * @author zhaochong on 2022/12/11 16:59
 */
public class MediumNumberOfArithmeticSlices {

	public static void main(String[] args) {
		int[] arr = new int[]{1, 2, 3, 4, 6, 8, 10};
		System.out.println(numberOfArithmeticSlicesV1(arr));
		System.out.println(numberOfArithmeticSlicesV2(arr));
	}

	public static int numberOfArithmeticSlicesV1(int[] nums) {
		if (nums.length < 3) {
			return 0;
		}

		int[] d = new int[nums.length];
		d[0] = 0;
		d[1] = 0;
		for (int i = 2; i < nums.length; i++) {
			// 符合等差条件
			if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
				// 3个是一个等差数组
				// 4个新增两个等差数组
				// 5个新增三个等差数组
				d[i] = d[i - 1] + 1;
			} else {
				d[i] = 0;
			}
		}

		// 加和
		int all = 0;
		for (int i : d) {
			all += i;
		}
		return all;
	}

	public static int numberOfArithmeticSlicesV2(int[] nums) {
		if (nums.length < 3) {
			return 0;
		}

		int continuousCount = 0;
		int accCount = 0;
		for (int i = 2; i < nums.length; i++) {
			// 符合等差条件
			if (nums[i] - nums[i - 1] == nums[i - 1] - nums[i - 2]) {
				continuousCount++;
				accCount += continuousCount;
			} else {
				continuousCount = 0;
			}
		}

		return accCount;
	}
}
