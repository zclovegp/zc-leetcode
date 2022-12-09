package dynamic;

/**
 * 198. 打家劫舍
 * 中等
 * 2.4K
 * 相关企业
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * <p>
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 * 偷窃到的最高金额 = 1 + 3 = 4 。
 * 示例 2：
 * <p>
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 * 偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 *
 * @author zhaochong on 2022/12/8 10:10
 */
public class EasyThief {

	public static void main(String[] args) {
		System.out.println(rob(new int[]{1, 2, 3, 1}));
		System.out.println(rob(new int[]{2, 7, 9, 3, 1}));

		System.out.println(doRob(new int[]{1, 2, 3, 1}));
		System.out.println(doRob(new int[]{2, 7, 9, 3, 1}));
	}

	public static int rob(int[] nums) {
		return rob(nums.length, nums);
	}

	public static int rob(int k, int[] nums) {

		if (k == 1) {
			return nums[0];
		}

		if (k == 2) {
			return Math.max(nums[0], nums[1]);
		}

		// f(k) = max( f(k-1)【不偷最后一家】, f(k-2)+h(k-1)【偷最后一家】 )
		return Math.max(rob(k - 1, nums), rob(k - 2, nums) + nums[k - 1]);
	}

	public static int doRob(int[] nums) {
		if (nums.length == 1) {
			return nums[0];
		}

		if (nums.length == 2) {
			return Math.max(nums[0], nums[1]);
		}

		int notRobLastPre = nums[0];
		int notRobLast = Math.max(nums[0], nums[1]);
		int current = 0;
		for (int i = 2; i < nums.length; i++) {
			// max(不偷i, 偷i)
			current = Math.max(notRobLast, notRobLastPre + nums[i]);
			notRobLastPre = notRobLast;
			notRobLast = current;
		}

		return current;
	}
}
