package 排序;

/**
 * @author zhaochong on 2023/5/29 17:01
 */

/**
 * @author zhaochong on 2023/5/29 16:59
 */
public class FindMidNum {

	public static void main(String[] args) {
		int[] input = new int[]{1, 2, 2, 4, 4, 5, 5, 7};
		System.out.println(getMidNum(input));
	}

	public static int getMidNum(int[] arr) {
		if (arr.length == 0) {
			return 0;
		}
		if (arr.length == 1) {
			return arr[0];
		}

		DivideResult result = divideArr(arr);

		sort(result.secondArr);

		int[] finalResult = merge(result.firstArr, result.secondArr);

		return finalResult[finalResult.length / 2];
	}

	public static DivideResult divideArr(int[] arr) {

		int firstDownIndex = -1;
		for (int i = 1; i < arr.length; i++) {
			if (arr[i] < arr[i - 1]) {
				firstDownIndex = i - 1;
				break;
			}
		}

		if (firstDownIndex == -1) {
			DivideResult divideResult = new DivideResult();
			divideResult.firstArr = arr;
			divideResult.secondArr = new int[]{};
			return divideResult;
		}

		DivideResult divideResult = new DivideResult();

		int[] firstArr = new int[firstDownIndex + 1];
		int[] secondArr = new int[arr.length - firstDownIndex];

		copy(0, firstDownIndex, arr, firstArr);
		copy(firstDownIndex + 1, arr.length - 1, arr, secondArr);

		divideResult.firstArr = firstArr;
		divideResult.secondArr = secondArr;
		return divideResult;
	}

	public static void copy(int startIncludeIndex, int endIncludeIndex, int[] arr, int[] resultArr) {
		int j = 0;
		for (int i = startIncludeIndex; i <= endIncludeIndex; i++) {
			resultArr[j] = arr[i];
			j++;
		}
	}

	public static void sort(int[] arr) {
		if (arr.length <= 1) {
			return;
		}
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int tmp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = tmp;
				}
			}
		}
	}

	public static int[] merge(int[] arr1, int[] arr2) {
		if (arr1.length == 0) {
			return arr2;
		}
		if (arr2.length == 0) {
			return arr1;
		}

		int index1 = 0;
		int index2 = 0;
		int resultIndex = 0;
		int[] resultArr = new int[arr1.length + arr2.length];
		while (true) {
			if (index1 >= arr1.length && index2 >= arr2.length) {
				break;
			}

			if (index1 < arr1.length && index2 < arr2.length) {
				if (arr1[index1] < arr2[index2]) {
					resultArr[resultIndex] = arr1[index1];
					index1++;
					resultIndex++;
				} else {
					resultArr[resultIndex] = arr2[index2];
					index2++;
					resultIndex++;
				}
			}

			if (index1 < arr1.length) {
				resultArr[resultIndex] = arr1[index1];
				index1++;
				resultIndex++;
			}

			if (index2 < arr2.length) {
				resultArr[resultIndex] = arr2[index2];
				index2++;
				resultIndex++;
			}
		}

		return resultArr;
	}
}

class DivideResult {
	public int[] firstArr;
	public int[] secondArr;
}

