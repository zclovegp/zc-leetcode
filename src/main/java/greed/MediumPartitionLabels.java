package greed;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 🔥在处理数组前，统计一遍信息(如频率、个数、第一次出现位置、最后一次出现位置等)可 以使题目难度大幅降低🔥
 * <p>
 * 符串 S 由小写字母组成。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。返回一个表示每个字符串片段的长度的列表。
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：S = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca", "defegde", "hijhklij"。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 的划分是错误的，因为划分的片段数较少。
 *  
 * <p>
 * 提示：
 * <p>
 * S的长度在[1, 500]之间。
 * S只包含小写字母 'a' 到 'z' 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/partition-labels
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/20 09:33
 */
public class MediumPartitionLabels {

	public static void main(String[] args) {
		System.out.println(partitionLabels("ababcbacadefegdehijhklij"));
	}

	public static List<Integer> partitionLabels(String s) {

		char[] arr = s.toCharArray();
		if (arr.length == 1) {
			List<Integer> result = new ArrayList<>();
			result.add(1);
			return result;
		}

		// 统计每个字符首次和最后一次出现的位置
		Map<Character, int[]> cFirstStartWithLastEndIndex = new HashMap<>();
		for (int i = 0; i < arr.length; i++) {
			int[] startWithEnd = cFirstStartWithLastEndIndex.get(arr[i]);
			if (startWithEnd == null) {
				cFirstStartWithLastEndIndex.put(arr[i], new int[]{i, i});
			} else {
				startWithEnd[1] = i;
				cFirstStartWithLastEndIndex.put(arr[i], startWithEnd);
			}
		}

		// 排序下
		List<int[]> result = new ArrayList<>();
		List<int[]> sortedStartWithLastEndIndexes = cFirstStartWithLastEndIndex.values().stream()
				.sorted(Comparator.comparing(x -> x[0]))
				.collect(Collectors.toList());

		// 拆分后的区间
		for (int[] startWithLastEndIndex : sortedStartWithLastEndIndexes) {

			if (result.size() == 0) {
				result.add(startWithLastEndIndex);
				continue;
			}

			boolean existOverLap = false;
			for (int[] existStartWithLastEndIndex : result) {
				// 有交叉
				if (startWithLastEndIndex[0] < existStartWithLastEndIndex[1] && startWithLastEndIndex[1] > existStartWithLastEndIndex[0]) {
					existStartWithLastEndIndex[0] = Math.min(startWithLastEndIndex[0], existStartWithLastEndIndex[0]);
					existStartWithLastEndIndex[1] = Math.max(startWithLastEndIndex[1], existStartWithLastEndIndex[1]);
					existOverLap = true;
				}
			}
			// 无交叉
			if (!existOverLap) {
				result.add(startWithLastEndIndex);
			}
		}

		return result.stream()
				.map(x -> x[1] - x[0] + 1)
				.collect(Collectors.toList());
	}
}
