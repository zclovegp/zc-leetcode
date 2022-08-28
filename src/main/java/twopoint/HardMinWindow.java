package twopoint;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * <p>
 *  
 * <p>
 * 注意：
 * <p>
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 示例 2：
 * <p>
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 示例 3:
 * <p>
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length, t.length <= 105
 * s 和 t 由英文字母组成
 *  
 * <p>
 * 进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-window-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/17 22:26
 */
public class HardMinWindow {

	public static void main(String[] args) {
		System.out.println(minWindow("ADOBECODEBANC", "ABC"));
	}


	public static String minWindow(String s, String t) {

		char[] fromArray = s.toCharArray();
		char[] targetArray = t.toCharArray();

		// 设置目标字符的个数
		Map<Character, Integer> charWithCount = new HashMap<>();
		for (char c : targetArray) {
			charWithCount.put(c, charWithCount.getOrDefault(c, 0) + 1);
		}

		Map<Character, Integer> windowTargetCharWithCount = new HashMap<>();
		int windowCharLeft = charWithCount.size();
		int leftIndex = 0;
		int[] minStartEnd = new int[]{0, Integer.MAX_VALUE};
		for (int rightIndex = 0; rightIndex < fromArray.length; rightIndex++) {

			char curRChar = fromArray[rightIndex];

			Integer targetRCharCount = charWithCount.get(curRChar);
			if (targetRCharCount != null) {

				// 累加目标char
				addWindowChar(windowTargetCharWithCount, curRChar);

				// 右指针字符在窗口内已满
				if (Objects.equals(windowTargetCharWithCount.get(curRChar), targetRCharCount)) {
					windowCharLeft--;
				}

				// 本窗口所有字符已满，挪动左指针
				while (windowCharLeft <= 0) {

					char curLChar = fromArray[leftIndex];
					Integer targetLCharCount = charWithCount.get(curLChar);
					if (targetLCharCount != null) {

						// 移除目标char
						removeWindowChar(windowTargetCharWithCount, curLChar);

						// 左指针字符在窗口内缺失
						Integer curLCharCount = windowTargetCharWithCount.getOrDefault(curLChar, 0);
						if (curLCharCount < targetLCharCount) {
							windowCharLeft++;

							// 可替换最小子串
							if ((rightIndex - leftIndex) < minStartEnd[1] - minStartEnd[0]) {
								minStartEnd[0] = leftIndex;
								minStartEnd[1] = rightIndex;
							}
						}
					}

					leftIndex++;
				}
			}
		}

		int start = minStartEnd[0];
		int end = minStartEnd[1];
		if (start == 0 && end == Integer.MAX_VALUE) {
			return "";
		} else {
			char[] result = new char[end - start + 1];
			int index = 0;
			for (int i = 0; i < fromArray.length; i++) {
				if (i >= start && i <= end) {
					result[index] = fromArray[i];
					index++;
				}
			}
			return new String(result);
		}
	}

	private static void removeWindowChar(Map<Character, Integer> windowTargetCharWithCount, char currentChar) {
		windowTargetCharWithCount.put(currentChar, windowTargetCharWithCount.getOrDefault(currentChar, 0) - 1);
	}

	private static void addWindowChar(Map<Character, Integer> windowTargetCharWithCount, char currentChar) {
		windowTargetCharWithCount.put(currentChar, windowTargetCharWithCount.getOrDefault(currentChar, 0) + 1);
	}
}
