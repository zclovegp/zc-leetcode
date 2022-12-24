package 排序;

import java.util.Arrays;

/**
 * 每次选最小的，和外层i交换
 *
 * @author zhaochong on 2022/12/24 10:28
 */
public class SelectSort {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(sort(new int[]{3, 1, 2, 5, 9, 2, 1})));
	}

	public static int[] sort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {

			int minIndex = i;
			for (int j = i; j < arr.length; j++) {
				if (arr[j] < arr[minIndex]) {
					minIndex = j;
				}
			}

			if (minIndex != i) {
				int tmp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = tmp;
			}
		}
		return arr;
	}
}
