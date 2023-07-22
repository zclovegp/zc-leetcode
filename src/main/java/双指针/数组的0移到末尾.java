package 双指针;

import java.util.Arrays;

/**
 * 把数组中的 0 移到后面 [1, 0, 3, 0, 2, 0, 4, 0, 0] => [1, 3, 2, 4, 0, 0, 0, 0]，
 * 时间 O(n)，空间 O(1)，非 0 的先后顺序不变
 *
 * @author zhaochong on 2023/7/21 11:11
 */
public class 数组的0移到末尾 {

	public static void main(String[] args) {
		int[] arr = new int[]{0, 1, 0, 3, 0, 2, 0, 4, 0, 0};
		moveZeroToEnd(arr);
		System.out.println(Arrays.toString(arr));
	}

	public static void moveZeroToEnd(int[] arr) {
		if (arr == null || arr.length <= 1) {
			return;
		}

		// j遇到0不动，等着i遇到非零的时候带着他一起移动
		int j = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != 0) {
				arr[j] = arr[i];
				j++;
			}
		}

		// 尾部统一归零
		for (int i = j; i < arr.length; i++) {
			arr[i] = 0;
		}
	}
}
