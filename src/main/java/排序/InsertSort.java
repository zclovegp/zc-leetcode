package 排序;

import java.util.Arrays;

/**
 * 每次外层的元素+1，向前i~0的位置尝试逐一交换
 *
 * @author zhaochong on 2022/12/24 10:36
 */
public class InsertSort {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(sort(new int[]{5, 4, 3, 2, 1})));
	}

	public static int[] sort(int[] arr) {
		if (arr.length <= 1) {
			return arr;
		}
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i + 1; j > 0; j--) {
				if (arr[j] < arr[j - 1]) {
					int tmp = arr[j];
					arr[j] = arr[j - 1];
					arr[j - 1] = tmp;
				}
			}
		}
		return arr;
	}
}
