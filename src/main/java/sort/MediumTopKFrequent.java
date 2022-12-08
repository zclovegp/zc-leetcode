package sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author zhaochong on 2022/11/14 15:19
 */
public class MediumTopKFrequent {

	public static void main(String[] args) {
		int[] xx = topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2);
		System.out.println(Arrays.toString(xx));
	}

	public static int[] topKFrequent(int[] nums, int k) {

		Map<Integer, Integer> numWithCount = new HashMap<>();
		for (int num : nums) {
			numWithCount.put(num, numWithCount.getOrDefault(num, 0) + 1);
		}

		List<Integer> result = numWithCount.entrySet()
				.stream()
				.sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
				.limit(k)
				.map(Map.Entry::getKey)
				.collect(Collectors.toList());

		int[] resultArr = new int[result.size()];
		for (int i = 0; i < result.size(); i++) {
			resultArr[i] = result.get(i);
		}

		return resultArr;
	}
}
