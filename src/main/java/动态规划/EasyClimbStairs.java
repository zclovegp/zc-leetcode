package 动态规划;

/**
 * @author zhaochong on 2022/11/15 16:49
 */
public class EasyClimbStairs {

	public static void main(String[] args) {
		System.out.println(climbStairs(6));
		System.out.println(climbStairs2(6));
	}

	/**
	 * 斐波那契数列
	 */
	public static int climbStairs(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		// 爬的所有楼梯方法数 = 最后走到了n-1的台阶 和 最后走到n-2的台阶
		return climbStairs(n - 1) + climbStairs(n - 2);
	}

	/**
	 * 斐波那契数列正着写
	 */
	public static int climbStairs2(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}

		int prePre = 1;
		int pre = 2;
		int result = 0;
		for (int i = 3; i <= n; i++) {
			result = prePre + pre;
			prePre = pre;
			pre = result;
		}
		return result;
	}
}
