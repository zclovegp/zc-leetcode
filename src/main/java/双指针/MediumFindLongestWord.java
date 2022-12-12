package 双指针;

import com.google.common.collect.ImmutableList;

import java.util.ArrayList;
import java.util.List;

/**
 * 524. 通过删除字母匹配到字典里最长单词
 * 中等
 * 322
 * 相关企业
 * 给你一个字符串 s 和一个字符串数组 dictionary ，找出并返回 dictionary 中最长的字符串，该字符串可以通过删除 s 中的某些字符得到。
 * <p>
 * 如果答案不止一个，返回长度最长且字母序最小的字符串。如果答案不存在，则返回空字符串。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "abpcplea", dictionary = ["ale","apple","monkey","plea"]
 * 输出："apple"
 * 示例 2：
 * <p>
 * 输入：s = "abpcplea", dictionary = ["a","b","c"]
 * 输出："a"
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 1000
 * 1 <= dictionary.length <= 1000
 * 1 <= dictionary[i].length <= 1000
 * s 和 dictionary[i] 仅由小写英文字母组成
 *
 * @author zhaochong on 2022/8/24 21:58
 */
public class MediumFindLongestWord {

	public static void main(String[] args) {
		System.out.println(findLongestWordV2("wordgoodgoodgoodbestword", ImmutableList.of("word", "good", "best", "good")));
	}

	public static String findLongestWordV2(String needDeleteStr, List<String> targetStrList) {
		List<String> matchStrList = new ArrayList<>();

		for (String targetStr : targetStrList) {
			char[] targetCharArr = targetStr.toCharArray();
			char[] needDeleteCharArr = needDeleteStr.toCharArray();

			int targetIndex = 0;
			int needDeleteIndex = 0;

			if (needDeleteCharArr.length < targetCharArr.length) {
				continue;
			}

			while (needDeleteIndex < needDeleteCharArr.length && targetIndex < targetCharArr.length) {

				// 字符相等，target扫描指针才向后移动
				if (targetCharArr[targetIndex] == needDeleteCharArr[needDeleteIndex]) {
					targetIndex++;
				}
				needDeleteIndex++;

				if (targetIndex == targetCharArr.length) {
					matchStrList.add(targetStr);
				}
			}
		}

		if (matchStrList.size() == 0) {
			return "";
		}

		// 选字符串长的
		// 其次选字符小的
		String result = "";
		for (String resultStr : matchStrList) {
			if (result.equals("")) {
				result = resultStr;
				continue;
			}

			// 长度优先
			if (resultStr.length() > result.length()) {
				result = resultStr;
				continue;
			}

			// 字母小优先
			if (resultStr.length() == result.length()) {
				boolean enableReplace = false;
				for (int i = 0; i < resultStr.length(); i++) {
					if (resultStr.charAt(i) != result.charAt(i)) {
						enableReplace = resultStr.charAt(i) < result.charAt(i);
						break;
					}
				}
				if (enableReplace) {
					result = resultStr;
				}
			}
		}
		return result;
	}
}
