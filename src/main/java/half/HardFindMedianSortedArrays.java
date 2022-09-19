package half;

/**
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。
 * <p>
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums1 = [1,3], nums2 = [2]
 * 输出：2.00000
 * 解释：合并数组 = [1,2,3] ，中位数 2
 * 示例 2：
 * <p>
 * 输入：nums1 = [1,2], nums2 = [3,4]
 * 输出：2.50000
 * 解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
 * <p>
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * nums1.length == m
 * nums2.length == n
 * 0 <= m <= 1000
 * 0 <= n <= 1000
 * 1 <= m + n <= 2000
 * -106 <= nums1[i], nums2[i] <= 106
 * <p>
 * 核心思路：求两个数组的中位数，可以转换为求第K小的元素
 *
 * @author zhaochong on 2022/9/19 19:11
 */
public class HardFindMedianSortedArrays {

	public static void main(String[] args) {
		System.out.println(findMedianSortedArrays(new int[]{1, 3}, new int[]{}));
	}

	public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
		int totalLength = nums1.length + nums2.length;
		// 偶数
		int midIndex = totalLength / 2;
		if (totalLength % 2 == 0) {
			// 寻找第K小的元素
			return (double) (getKthElement(nums1, nums2, midIndex) + getKthElement(nums1, nums2, midIndex + 1)) / 2;
		}
		// 奇数
		else {
			// 寻找第K小的元素
			return getKthElement(nums1, nums2, midIndex + 1);
		}
	}

	/**
	 * 找到第K小元素，转换成比较nums1[k/2-1]和nums2[k/2-1]
	 */
	public static int getKthElement(int[] nums1, int[] nums2, int k) {

		int length1 = nums1.length;
		int length2 = nums2.length;
		// 数组1的剔除指针
		int index1 = 0;
		// 数组2的剔除指针
		int index2 = 0;

		while (true) {
			// 第一个数组剔除完了
			if (index1 == length1) {
				return nums2[index2 + k - 1];
			}
			// 第二个数组剔除完了
			if (index2 == length2) {
				return nums1[index1 + k - 1];
			}
			// 求剔除后第K小的元素，取当前两个指针中最小的元素即可
			if (k == 1) {
				return Math.min(nums1[index1], nums2[index2]);
			}

			// 正常情况
			int half = k / 2;
			int newIndex1 = Math.min(index1 + half, length1) - 1;
			int newIndex2 = Math.min(index2 + half, length2) - 1;
			int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
			if (pivot1 <= pivot2) {
				k -= (newIndex1 - index1 + 1);
				index1 = newIndex1 + 1;
			} else {
				k -= (newIndex2 - index2 + 1);
				index2 = newIndex2 + 1;
			}
		}
	}
}
