package 二分法;

/**
 * 81. 搜索旋转排序数组 II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 * <p>
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 * <p>
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 * <p>
 * 你必须尽可能减少整个操作步骤。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 *
 * @author zhaochong on 2022/9/12 11:19
 */
public class MediumSearch {

	public static void main(String[] args) {
		System.out.println(search(new int[]{1, 0, 1, 1, 1}, 0));
	}

	public static boolean search(int[] nums, int target) {

		int l = 0;
		int r = nums.length - 1;
		while (l <= r) {

			int mid = (l + r) / 2;
			if (nums[mid] == target) {
				return true;
			}

			// 无法判断左还是右有序
			if (nums[mid] == nums[l]) {
				l++;
			}

			// 左边有序
			else if (nums[mid] > nums[l]) {
				// 左边有序，判断是不是存在于左边，如果是，缩短r；否则换另一边，即缩短l
				if (target >= nums[l] && target <= nums[mid]) {
					r = mid - 1;
				} else {
					l = mid + 1;
				}
			}

			// 右边有序
			else {
				if (target >= nums[mid] && target <= nums[r]) {
					l = mid + 1;
				} else {
					r = mid - 1;
				}
			}
		}
		return false;
	}
}
