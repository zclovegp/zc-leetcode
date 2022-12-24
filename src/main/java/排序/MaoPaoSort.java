package 排序;

import java.util.Arrays;

/**
 * 相邻的比较，大的和下一个交换，每次把最大的放到最后，这样下一轮循环可以少处理一些元素
 *
 * @author zhaochong on 2022/12/24 10:19
 */
public class MaoPaoSort {

	public static void main(String[] args) {
		System.out.println(Arrays.toString(sort(new int[]{5, 3, 1, 6, 4})));
		System.out.println(Arrays.toString(sort(new int[]{})));
	}

	public static int[] sort(int[] arr) {

		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
		return arr;
	}
}
