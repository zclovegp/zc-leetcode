package 排序;

import java.util.Arrays;

/**
 * @author zhaochong on 2022/10/6 22:34
 */
public class FastSort {

	public static void main(String[] args) {
		int[] targetArr = new int[]{6, 6, 2, 7, 9, 8, 4, 5, 10, 8};
		fastSort(targetArr, 0, 9);
		System.out.println(Arrays.toString(targetArr));
	}

	public static void fastSort(int[] targetArr, int staticLeft, int staticRight) {
		if (staticLeft >= staticRight) {
			return;
		}

		int targetIndex = staticLeft;
		int targetValue = targetArr[targetIndex];
		int left = staticLeft;
		int right = staticRight;
		String startFromLeftOrRight = "right";

		// 一次快排结束
		while (left < right) {
			if (startFromLeftOrRight.equals("right")) {
				if (targetValue <= targetArr[right]) {
					right--;
				} else {
					switchLeftAndRight(targetArr, left, right);
					startFromLeftOrRight = "left";
					targetIndex = right;
				}
			} else {
				if (targetValue >= targetArr[left]) {
					left++;
				} else {
					switchLeftAndRight(targetArr, left, right);
					startFromLeftOrRight = "right";
					targetIndex = left;
				}
			}
		}

		fastSort(targetArr, staticLeft, targetIndex - 1);
		fastSort(targetArr, targetIndex + 1, staticRight);
	}

	private static void switchLeftAndRight(int[] targetArr, int left, int right) {
		int tmp = targetArr[left];
		targetArr[left] = targetArr[right];
		targetArr[right] = tmp;
	}
}
