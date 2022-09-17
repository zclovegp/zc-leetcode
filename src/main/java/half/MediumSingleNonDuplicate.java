package half;

/**
 * 540. 有序数组中的单一元素
 * 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * <p>
 * 请你找出并返回只出现一次的那个数。
 * <p>
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 * <p>
 * <p>
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [1,1,2,3,3,4,4,8,8]
 * 输出: 2
 * 示例 2:
 * <p>
 * 输入: nums =  [3,3,7,7,10,11,11]
 * 输出: 10
 * <p>
 * <p>
 * 提示:
 * <p>
 * 1 <= nums.length <= 105
 * 0 <= nums[i] <= 105
 *
 * @author zhaochong on 2022/9/17 08:44
 */
public class MediumSingleNonDuplicate {

	public static void main(String[] args) {
		System.out.println(singleNonDuplicate(new int[]{1, 1, 2, 2, 3}));
	}

	public static int singleNonDuplicate(int[] nums) {

		if (nums.length == 1) {
			return nums[0];
		}

		int l = 0;
		int r = nums.length - 1;

		while (l <= r) {

			if (l == r) {
				return nums[l];
			}

			int mid = (l + r) / 2;

			// 如果=左边，看左边是不是偶数长度，如果是偶数长度，就说明再另一边，如果是奇数长度，说明在这一边
			if (nums[mid] == nums[mid - 1]) {
				// 奇数个
				if ((l + mid) % 2 == 0) {
					r = mid - 2;
				} else {
					l = mid + 1;
				}
			}

			// 如果=右边，看右边是不是偶数长度，如果是偶数长度，就说明再另一边，如果是奇数长度，说明在这一边
			else if (nums[mid] == nums[mid + 1]) {
				// 奇数个
				if ((r + mid) % 2 == 0) {
					l = mid + 2;
				} else {
					r = mid - 1;
				}
			} else {
				return nums[mid];
			}
		}

		throw new RuntimeException("不可能走到这里");
	}
}
