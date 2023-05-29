package 动态规划;

/**
 * @author zhaochong on 2023/5/25 19:58
 */
public class MediumLengthOfLIS {

	public static void main(String[] args) {
		System.out.println(lengthOfLIS(new int[]{1, 2, 3, 5, 1, 2, 9}));
	}

	public static int lengthOfLIS(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		if (nums.length == 1) {
			return 1;
		}

		int[] d = new int[nums.length];
		for (int i = 0; i < nums.length; i++) {
			int maxItem = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j] && ((d[j] + 1) > maxItem)) {
					maxItem = d[j] + 1;
				}
			}
			d[i] = maxItem;
		}

		int maxItem = 0;
		for (int i = 0; i < d.length; i++) {
			if (d[i] > maxItem) {
				maxItem = d[i];
			}
		}
		return maxItem;
	}
}
