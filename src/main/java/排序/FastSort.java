package 排序;

import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author zhaochong on 2022/10/6 22:34
 */
public class FastSort {

	public static void main(String[] args) {
		int[] targetArr = new int[]{8, 8, 5, 1, 3, 2, 6, 3, 4, 9, 6, 6, 2, 7, 9, 8, 4, 5, 10, 8};
		fastSortV2(targetArr);
		System.out.println(Arrays.toString(targetArr));
	}

	/**
	 * 递推实现
	 */
	public static void fastSortV2(int[] targetArr) {
		Queue<int[]> targetQueue = new LinkedBlockingQueue<>();
		int[] startWithEnd = new int[]{0, targetArr.length - 1};
		targetQueue.add(startWithEnd);

		while (true) {

			int[] leftWithRight = targetQueue.poll();
			if (leftWithRight == null) {
				break;
			}

			int leftIndex = leftWithRight[0];
			int rightIndex = leftWithRight[1];
			String forward = "right";

			// 一次快排
			while (true) {
				if (leftIndex >= rightIndex) {
					// 往队列里新增新的计算索引
					if (leftIndex - 1 > leftWithRight[0]) {
						targetQueue.add(new int[]{leftWithRight[0], leftIndex - 1});
					}
					if (leftWithRight[1] > leftIndex + 1) {
						targetQueue.add(new int[]{leftIndex + 1, leftWithRight[1]});
					}
					break;
				}

				if ("right".equals(forward)) {
					if (targetArr[leftIndex] <= targetArr[rightIndex]) {
						rightIndex--;
					} else {
						swapValue(targetArr, leftIndex, rightIndex);
						forward = "left";
					}
				}

				if ("left".equals(forward)) {
					if (targetArr[rightIndex] >= targetArr[leftIndex]) {
						leftIndex++;
					} else {
						swapValue(targetArr, leftIndex, rightIndex);
						forward = "right";
					}
				}
			}
		}
	}

	private static void swapValue(int[] targetArr, int leftIndex, int rightIndex) {
		int tmp = targetArr[leftIndex];
		targetArr[leftIndex] = targetArr[rightIndex];
		targetArr[rightIndex] = tmp;
	}
}
