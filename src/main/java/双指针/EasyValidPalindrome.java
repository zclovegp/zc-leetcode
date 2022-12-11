package 双指针;

/**
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: s = "aba"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: s = "abca"
 * 输出: true
 * 解释: 你可以删除c字符。
 * 示例 3:
 * <p>
 * 输入: s = "abc"
 * 输出: false
 *  
 * <p>
 * 提示:
 * <p>
 * 1 <= s.length <= 105
 * s 由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-palindrome-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/23 22:33
 */
public class EasyValidPalindrome {

	public static void main(String[] args) {
		System.out.println(validPalindrome("ebcbbececabbacecbbcbe"));
	}

	public static boolean validPalindrome(String s) {
		char[] strArr = s.toCharArray();
		if (strArr.length <= 1) {
			return true;
		}

		int startIndex = 0;
		int endIndex = strArr.length - 1;
		int skipCount = 1;

		boolean validPalindrome = doValidPalindrome(strArr, startIndex, endIndex, skipCount, true);
		if (validPalindrome) {
			return true;
		} else {
			return doValidPalindrome(strArr, startIndex, endIndex, skipCount, false);
		}
	}

	private static boolean doValidPalindrome(char[] strArr, int startIndex, int endIndex, int skipCount, boolean firstRemoveRight) {
		while (true) {

			if (startIndex >= endIndex) {
				return true;
			}

			// 相等
			if (strArr[startIndex] == strArr[endIndex]) {
				startIndex++;
				endIndex--;
			} else {

				boolean removeRight = startIndex <= endIndex - 1 && strArr[startIndex] == strArr[endIndex - 1] && skipCount == 1;
				boolean removeLeft = startIndex <= endIndex - 1 && strArr[startIndex + 1] == strArr[endIndex] && skipCount == 1;

				// 左右都可删
				if (removeRight && removeLeft) {
					if (firstRemoveRight) {
						endIndex -= 2;
						startIndex++;
					} else {
						startIndex += 2;
						endIndex--;
					}
					skipCount--;
					continue;
				}

				// 只能删右
				if (removeRight) {
					endIndex -= 2;
					startIndex++;
					skipCount--;
					continue;
				}

				// 只能删左
				if (removeLeft) {
					startIndex += 2;
					endIndex--;
					skipCount--;
					continue;
				}

				return false;
			}
		}
	}
}
