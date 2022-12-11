package 二分法;

/**
 * 154. 寻找旋转排序数组中的最小值 II
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 * <p>
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 * <p>
 * 你必须尽可能减少整个过程的操作步骤。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [1,3,5]
 * 输出：1
 * 示例 2：
 * <p>
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 * <p>
 * <p>
 * 提示：
 * <p>
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 *
 * @author zhaochong on 2022/9/13 20:25
 */
public class HardFindMin {

	public static void main(String[] args) {
		System.out.println(findMin(new int[]{3, 1}));
	}

	public static int findMin(int[] nums) {
		int start = 0;
		int end = nums.length - 1;
		int currentMin = nums[0];

		while (start <= end) {

			int mid = (start + end) / 2;

			// 无法判定左边还是右边有序
			if (nums[start] == nums[mid]) {
				if (nums[start] < currentMin) {
					currentMin = nums[start];
				}
				start++;
				continue;
			}

			// 左边有序
			if (nums[mid] > nums[start]) {
				if (nums[start] < currentMin) {
					currentMin = nums[start];
				}

				// 把注意放到另一侧无序列的
				start = mid + 1;
			}
			// 右边有序
			else {
				if (nums[mid] < currentMin) {
					currentMin = nums[mid];
				}

				// 把注意放到另一侧无序列的
				end = mid - 1;
			}
		}

		return currentMin;
	}
}
