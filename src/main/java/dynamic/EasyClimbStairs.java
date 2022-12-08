package dynamic;

/**
 * @author zhaochong on 2022/11/15 16:49
 */
public class EasyClimbStairs {

	public static void main(String[] args) {
		System.out.println(climbStairs(3));
		System.out.println(climbStairs2(3));
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
		return climbStairs(n - 1) + climbStairs(n - 2);
	}

	/**
	 * 斐波那契数列正着写
	 */
	public static int climbStairs2(int n) {
		if (n <= 2) {
			return n;
		}
		int pre1 = 2;
		int pre2 = 1;
		int cur = 0;
		for (int i = 2; i < n; ++i) {
			cur = pre1 + pre2;
			pre2 = pre1;
			pre1 = cur;
		}
		return cur;
	}
}
