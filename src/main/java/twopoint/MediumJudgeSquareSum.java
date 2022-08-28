package twopoint;

/**
 * 给定一个非负整数 c ，你要判断是否存在两个整数 a 和 b，使得 a2 + b2 = c 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：c = 5
 * 输出：true
 * 解释：1 * 1 + 2 * 2 = 5
 * 示例 2：
 * <p>
 * 输入：c = 3
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= c <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sum-of-square-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/22 21:36
 */
public class MediumJudgeSquareSum {

	public static void main(String[] args) {
		System.out.println(judgeSquareSum(1000000));
		System.out.println(judgeSquareSum(1));
	}

	public static boolean judgeSquareSum(int c) {
		double a = 0;
		double b = Math.ceil(Math.sqrt(c));

		boolean judgeSquareSum = false;
		while (true) {
			double result = (a * a) + (b * b);
			if (result == c) {
				System.out.println(a + "-" + b);
				judgeSquareSum = true;
				break;
			}
			if (a == b) {
				break;
			}
			if (result > c) {
				b--;
			}
			if (result < c) {
				a++;
			}
		}
		return judgeSquareSum;
	}
}
