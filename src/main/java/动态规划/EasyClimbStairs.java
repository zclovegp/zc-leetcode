package ε¨ζθ§ε;

/**
 * π₯d[n] = d[n-1] + d[n-2]π₯
 *
 * @author zhaochong on 2022/11/15 16:49
 */
public class EasyClimbStairs {

	public static void main(String[] args) {
		System.out.println(climbStairs(6));
		System.out.println(climbStairs2(6));
		System.out.println(climbStairs3(6));
	}

	/**
	 * ζζ³’ι£ε₯ζ°ε
	 */
	public static int climbStairs(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}
		// η¬ηζζζ₯Όζ’―ζΉζ³ζ° = ζεθ΅°ε°δΊn-1ηε°ιΆ ε ζεθ΅°ε°n-2ηε°ιΆ
		return climbStairs(n - 1) + climbStairs(n - 2);
	}


	/**
	 * ζͺεη©Ίι΄δΌεηιζ¨
	 */
	public static int climbStairs2(int n) {
		if (n == 1) {
			return 1;
		}
		if (n == 2) {
			return 2;
		}

		int[] d = new int[n];
		d[0] = 1;
		d[1] = 2;
		for (int i = 2; i < n; i++) {
			d[i] = d[i - 1] + d[i - 2];
		}
		return d[d.length - 1];
	}

	/**
	 * ε·²εη©Ίι΄δΌεηιζ¨
	 */
	public static int climbStairs3(int n) {
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
