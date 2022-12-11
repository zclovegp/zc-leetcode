package 双指针;

import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 * 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * <p>
 * 如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * 示例 2：
 * <p>
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s 和 dictionary[i] 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-word-in-dictionary-through-deleting
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/24 21:58
 */
public class MediumFindLongestWord {

	public static void main(String[] args) {
		System.out.println(findLongestWord("abce", ImmutableList.of("abc", "abe")));
	}

	public static String findLongestWord(String s, List<String> dictionary) {

		String maxItem = "";

		for (String item : dictionary) {

			char[] waitDeleteArr = s.toCharArray();
			char[] baseArr = item.toCharArray();

			int dIndex = 0;
			int bIndex = 0;

			// 遍历待删除字符串和比较字符串
			while (true) {

				// 包含base字符，更大的扔到maxItem中
				if (bIndex >= baseArr.length) {
					if (item.length() > maxItem.length()) {
						maxItem = item;
					}
					// 长度相同，取字典序更低的
					else if (item.length() == maxItem.length()) {
						if (item.compareTo(maxItem) < 0) {
							maxItem = item;
						}
					}
					break;
				}

				// 不包含base字符
				if (dIndex >= waitDeleteArr.length) {
					break;
				}

				if (waitDeleteArr[dIndex] == baseArr[bIndex]) {
					dIndex++;
					bIndex++;
				} else {
					dIndex++;
				}
			}
		}
		return maxItem;
	}
}
