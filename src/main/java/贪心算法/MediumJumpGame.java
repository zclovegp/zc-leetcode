package 贪心算法;

/**
 * @author zhaochong on 2022/12/25 22:35
 */
public class MediumJumpGame {

	public static void main(String[] args) {
		int[] arr = new int[]{3, 2, 1};
		System.out.println(jump(arr));
	}

	public static int jump(int[] nums) {
		// 长度小于1，不用跳就到队尾
		if (nums.length <= 1) {
			return 0;
		}

		// 初始化参数
		int stepNum = 0;
		int index = 0;
		while (true) {

			// 如果index已经到队尾，就结束
			if (index >= nums.length - 1) {
				break;
			}

			// 走一次就到达终点了
			int jumpEndIndex = index + nums[index];
			if (jumpEndIndex >= nums.length - 1) {
				stepNum++;
				break;
			}

			// 选出可以跳的最远距离
			int tmpMaxPosition = 0;
			int tmpNextPosition = 0;
			for (int i = index + 1; i <= Math.min(nums.length - 1, jumpEndIndex); i++) {
				if ((i + nums[i]) > tmpMaxPosition) {
					tmpMaxPosition = i + nums[i];
					tmpNextPosition = i;
				}
			}

			index = Math.min(tmpNextPosition, nums.length - 1);
			stepNum++;
		}

		return stepNum;
	}
}
