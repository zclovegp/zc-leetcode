package 动态规划;

/**
 * @author zhaochong on 2023/6/25 22:54
 */
public class 最长公共子序列 {

	public static void main(String[] args) {
		System.out.println(getMaxChildSeq("abcd", "efcd"));
	}

	public static int getMaxChildSeq(String text1, String text2) {

		if (text1 == null || text1.toCharArray().length == 0 || text2 == null || text2.toCharArray().length == 0) {
			return 0;
		}

		int[][] d = new int[text1.length()][text2.length()];
		// y
		for (int j = 0; j < d.length; j++) {
			// x
			for (int i = 0; i < d[0].length; i++) {
				if (j - 1 >= 0 && i - 1 >= 0) {
					d[j][i] = Math.max(Math.max(d[j - 1][i - 1], d[j - 1][i]), d[j][i - 1]);
				} else if (j - 1 >= 0) {
					d[j][i] = d[j - 1][0];
				} else if (i - 1 >= 0) {
					d[j][i] = d[0][i - 1];
				}

				if (text1.charAt(j) == text2.charAt(i)) {
					d[j][i] = d[j][i] + 1;
				}
			}
		}

		for (int i = 0; i < d.length; i++) {
			for (int j = 0; j < d[0].length; j++) {
				System.out.print(" " + d[i][j]);
			}
			System.out.println();
		}

		return d[d.length - 1][d[0].length - 1];
	}
}
