package half;

/**
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * <p>
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * <p>
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 * 示例 2：
 * <p>
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 * 示例 3：
 * <p>
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/9/8 20:18
 */
public class MediumSearchRange {

	public static void main(String[] args) {
		int[] nums = new int[]{5, 7, 7, 8, 8, 10};
		searchRange(nums,8);
	}

	public static int[] searchRange(int[] nums, int target) {
		if (nums.length == 0) {
			return new int[]{-1, -1};
		}

		int startIndex = 0;
		int endIndex = nums.length - 1;
		int targetMinStart = -1;
		while (true) {
			int midIndex = startIndex + endIndex / 2;

			// 开始=结束
			if (startIndex == endIndex) {
				break;
			}

			// 目标值在左边
			if (nums[midIndex] > target) {
				endIndex = midIndex;
			}
			// 目标值在右边
			else if (nums[midIndex] < target) {
				startIndex = midIndex;
			}
			// 相等
			else {
				targetMinStart = midIndex;
				endIndex = midIndex;
			}
		}

		System.out.println(targetMinStart);
		return null;
	}
}
