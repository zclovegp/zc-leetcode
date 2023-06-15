package 双指针;

/**
 * @author zhaochong on 2023/6/15 08:52
 */
public class LongestSubstringWithoutRepeatingCharacters {
	public static void main(String[] args) {
		String s = "abcabcsosbbdkd";
		int length = lengthOfLongestSubstring(s);
		System.out.println(length);
	}

	public static int lengthOfLongestSubstring(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		int[] map = new int[128];
		int max = 0;
		int left = 0;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			left = Math.max(left, map[c]);
			map[c] = i + 1;
			max = Math.max(max, i - left + 1);
		}
		return max;
	}
}
