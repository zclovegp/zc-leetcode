package greed;

/**
 * 🔥此问题是二选一，除了可以换自己，还可以换另一个🔥
 * <p>
 * 给你一个长度为 n 的整数数组 nums ，请你判断在 最多 改变 1 个元素的情况下，该数组能否变成一个非递减数列。
 * <p>
 * 我们是这样定义一个非递减数列的： 对于数组中任意的 i (0 <= i <= n-2)，总满足 nums[i] <= nums[i + 1]。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [4,2,3]
 * 输出: true
 * 解释: 你可以通过把第一个 4 变成 1 来使得它成为一个非递减数列。
 * 示例 2:
 * <p>
 * 输入: nums = [4,2,1]
 * 输出: false
 * 解释: 你不能在只改变一个元素的情况下将其变为非递减数列。
 *  
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 104
 * -105 <= nums[i] <= 105
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/non-decreasing-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/21 16:47
 */
public class MediumCheckPossibilityIncrease {

	public static void main(String[] args) {
		System.out.println(checkPossibility(new int[]{4, 2, 1}));
		System.out.println(checkPossibility(new int[]{4, 3, 2}));
		System.out.println(checkPossibility(new int[]{5, 7, 1, 8}));
	}

	public static boolean checkPossibility(int[] nums) {
		if (nums.length <= 1) {
			return true;
		}

		int count = 0;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] < nums[i - 1]) {

				// 首次出现前面大的，可以直接将
				if (i == 1) {
					nums[i - 1] = nums[i];
					count++;
					continue;
				}

				if (nums[i] >= nums[i - 2]) {
					nums[i - 1] = nums[i - 2];
				} else {
					nums[i] = nums[i - 1];
				}

				count++;
			}
		}

		return count <= 1;
	}
}
