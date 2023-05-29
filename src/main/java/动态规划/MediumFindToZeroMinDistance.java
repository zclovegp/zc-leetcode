package 动态规划;

import java.util.Arrays;

/**
 * 542. 01 矩阵【正算一次，反再算一次】
 * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * 两个相邻元素间的距离为 1。
 * 🔥d[m][n] = min(d[m-1][n], d[m][n-1]) + 1🔥
 * 🔥d[m][n] = min(d[m][n], min(d[m+1][n], d[m][n+1]) + 1)🔥
 * 中等
 * 788
 * 相关企业
 * 给定一个由 0 和 1 组成的矩阵 mat ，请输出一个大小相同的矩阵，其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
 * <p>
 * 两个相邻元素间的距离为1
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * 输入：mat = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：[[0,0,0],[0,1,0],[0,0,0]]
 * <p>
 * 示例 2：
 * 输入：mat = [[0,0,0],[0,1,0],[1,1,1]]
 * 输出：[[0,0,0],[0,1,0],[1,2,1]]
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 104
 * 1 <= m * n <= 104
 * mat[i][j] is either 0 or 1.
 * mat 中至少有一个 0
 *
 * @author zhaochong on 2022/12/16 08:44
 */
public class MediumFindToZeroMinDistance {

	public static void main(String[] args) {
		int[][] targetArr = new int[][]{{0, 0, 0}, {0, 1, 0}, {1, 1, 1}};
		int[][] result = updateMatrix(targetArr);
		for (int[] ints : result) {
			System.out.println(Arrays.toString(ints));
		}
	}

	public static int[][] updateMatrix(int[][] mat) {
		int[][] d = new int[mat.length][mat[0].length];
		for (int m = 0; m < mat.length; m++) {
			for (int n = 0; n < mat[0].length; n++) {
				// 本身就是0，无需探索
				if (mat[m][n] == 0) {
					d[m][n] = 0;
				}
				// 本身非0，需动态规划
				else {
					if (m == 0 && n == 0) {
						d[m][n] = 100000;
					} else if (m == 0) {
						d[m][n] = d[m][n - 1] + 1;
					} else if (n == 0) {
						d[m][n] = d[m - 1][n] + 1;
					} else {
						d[m][n] = Math.min(d[m - 1][n], d[m][n - 1]) + 1;
					}
				}
			}
		}

		for (int m = mat.length - 1; m >= 0; m--) {
			for (int n = mat[0].length - 1; n >= 0; n--) {
				// 本身就是0，无需探索
				if (mat[m][n] == 0) {
					d[m][n] = 0;
				}
				// 本身非0，需动态规划
				else {
					if (m == mat.length - 1 && n == mat[0].length - 1) {
						// do nothing
					} else if (m == mat.length - 1) {
						d[m][n] = Math.min(d[m][n], d[m][n + 1] + 1);
					} else if (n == mat[0].length - 1) {
						d[m][n] = Math.min(d[m][n], d[m + 1][n] + 1);
					} else {
						d[m][n] = Math.min(d[m][n], Math.min(d[m + 1][n], d[m][n + 1]) + 1);
					}
				}
			}
		}

		return d;
	}
}
