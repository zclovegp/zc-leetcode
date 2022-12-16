package 动态规划;

/**
 * 64. 最小路径和🔥d[m][n] = min(d[m][n-1], d[m-1][n]) + h[m][n]🔥
 * 中等
 * 1.4K
 * 相关企业
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * <p>
 * 说明：每次只能向下或者向右移动一步。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 * 示例 2：
 * <p>
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 * @author zhaochong on 2022/12/14 09:00
 */
public class MediumTwoDimensionMinDistance {

	public static void main(String[] args) {
		int[][] arr = new int[][]{{1, 3, 1}, {1, 5, 1}, {4, 2, 1}};
		System.out.println(minPathSum(arr));
		System.out.println(minPathSumForEach(arr));
	}


	public static int minPathSum(int[][] grid) {
		return minPathSum(grid, grid.length - 1, grid[0].length - 1);
	}

	/**
	 * 一个m*n的矩阵，最短走到m-1*n-1的位置需要的距离
	 */
	public static int minPathSum(int[][] grid, int m, int n) {

		if (m == 0 && n == 0) {
			return grid[0][0];
		} else if (m == 0) {
			return minPathSum(grid, 0, n - 1) + grid[m][n];
		} else if (n == 0) {
			return minPathSum(grid, m - 1, 0) + grid[m][n];
		} else {
			// f(m,n) = MIN(f(m, n-1),f(m-1, n)) + h(m, n)
			return Math.min(minPathSum(grid, m - 1, n), minPathSum(grid, m, n - 1)) + grid[m][n];
		}
	}

	/**
	 * 递归转迭代
	 */
	public static int minPathSumForEach(int[][] grid) {
		// 递推数组
		int[][] d = new int[grid.length][grid[0].length];

		// 构建递推数组
		for (int m = 0; m < grid.length; m++) {
			for (int n = 0; n < grid[0].length; n++) {

				if (m == 0 && n == 0) {
					d[m][n] = grid[0][0];
				} else if (m == 0) {
					d[m][n] = d[m][n - 1] + grid[m][n];
				} else if (n == 0) {
					d[m][n] = d[m - 1][n] + grid[m][n];
				} else {
					// f(m,n) = MIN(f(m, n-1),f(m-1, n)) + h(m, n)
					d[m][n] = Math.min(d[m - 1][n], d[m][n - 1]) + grid[m][n];
				}
			}
		}
		return d[d.length - 1][d[0].length - 1];
	}
}
