package 动态规划;

/**
 * 221. 最大正方形🔥d[m][n] = Math.min(Math.min(d[m][n - 1], d[m - 1][n]), d[m - 1][n - 1]) + 1🔥
 * 中等
 * 1.3K
 * 相关企业
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
 * 输出：4
 * 示例 2：
 * 输入：matrix = [["0","1"],["1","0"]]
 * 输出：1
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 *
 * @author zhaochong on 2022/12/18 10:37
 */
public class MediumFindMaxSquare {

	public static void main(String[] args) {
		char[][] arr = new char[][]{{'1', '0', '1', '0', '0'}, {'1', '0', '1', '1', '1'}, {'1', '1', '1', '1', '1'}, {'1', '0', '0', '1', '0'}};
		System.out.println(maximalSquare(arr));
	}

	public static int maximalSquare(char[][] matrix) {
		int maximalSquare = 0;
		int[][] d = new int[matrix.length][matrix[0].length];
		for (int m = 0; m < matrix.length; m++) {
			for (int n = 0; n < matrix[0].length; n++) {
				if (matrix[m][n] != '1') {
					d[m][n] = 0;
				} else {
					if (m == 0 && n == 0) {
						d[m][n] = 1;
					} else if (m == 0) {
						d[m][n] = 1;
					} else if (n == 0) {
						d[m][n] = 1;
					} else {
						d[m][n] = Math.min(Math.min(d[m][n - 1], d[m - 1][n]), d[m - 1][n - 1]) + 1;
					}
				}

				if (d[m][n] > maximalSquare) {
					maximalSquare = d[m][n];
				}
			}
		}
		return maximalSquare * maximalSquare;
	}
}
