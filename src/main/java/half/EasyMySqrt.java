package half;

/**
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 4
 * 输出：2
 * 示例 2：
 * <p>
 * 输入：x = 8
 * 输出：2
 * 解释：8 的算术平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= x <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/sqrtx
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/26 19:43
 */
public class EasyMySqrt {

	public static void main(String[] args) {
		System.out.println(mySqrt(4));
		System.out.println(mySqrt(8));
		System.out.println(mySqrt(3));
		System.out.println(mySqrt(6));
	}

	public static int mySqrt(int x) {
		long left = 0;
		long right = x;
		long currentResult = 0;
		while (true) {
			long half = (left + right) / 2;

			long result = half * half;

			if (result == x) {
				return (int) half;
			}

			if (left > right) {
				return (int) currentResult;
			}

			if (result < x) {
				currentResult = half;
				left = half + 1;
			} else {
				right = half - 1;
			}
		}
	}
}
