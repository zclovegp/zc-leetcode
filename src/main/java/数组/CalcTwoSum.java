package 数组;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaochong on 2023/5/31 15:38
 */
public class CalcTwoSum {

	public static void main(String[] args) {
		String str1 = "349";
		String str2 = "96452";
		sum(str1, str2);
	}

	public static void sum(String str1, String str2) {
		char[] char1 = str1.toCharArray();
		char[] reverseChar1 = reverseArr(char1);

		char[] char2 = str2.toCharArray();
		char[] reverseChar2 = reverseArr(char2);

		char[] baseChar;
		char[] shortChar;
		if (reverseChar1.length > char2.length) {
			baseChar = reverseChar1;
			shortChar = reverseChar2;
		} else {
			baseChar = reverseChar2;
			shortChar = reverseChar1;
		}

		// 进位
		List<String> result = new ArrayList<>();
		int lastFlag = 0;
		for (int i = 0; i < baseChar.length; i++) {
			char baseI = baseChar[i];

			int baseInt = Integer.parseInt(baseI + "");

			if (i <= shortChar.length - 1) {
				int shortInt = Integer.parseInt(shortChar[i] + "");
				int tmpResult = baseInt + shortInt + lastFlag;
				if (tmpResult >= 10) {
					int r = tmpResult - 10;
					lastFlag = 1;
					result.add(r + "");
				} else {
					result.add(tmpResult + "");
					lastFlag = 0;
				}
			} else {
				int tmpResult = baseInt + lastFlag;
				if (tmpResult >= 10) {
					int r = tmpResult - 10;
					lastFlag = 1;
					result.add(r + "");
				} else {
					result.add(tmpResult + "");
					lastFlag = 0;
				}
			}
		}

		if (lastFlag != 0) {
			result.add("1");
		}

		String finalResult = "";
		for (int i = result.size() - 1; i >= 0; i--) {
			finalResult = finalResult + result.get(i);
		}

		System.out.println(finalResult);
	}

	public static char[] reverseArr(char[] arr) {
		char[] newArr = new char[arr.length];

		int j = 0;
		for (int i = arr.length - 1; i >= 0; i--) {
			newArr[j] = arr[i];
			j++;
		}
		return newArr;
	}
}
