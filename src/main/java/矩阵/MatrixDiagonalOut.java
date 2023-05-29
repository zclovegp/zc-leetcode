package 矩阵;

import java.util.Arrays;

/**
 * @author zhaochong on 2023/5/24 21:02
 */
public class MatrixDiagonalOut {

	/**
	 * 打印矩阵的对角
	 * <p>
	 * 1  2  3  4
	 * 5  6  7  8
	 * 9  10 11 12
	 * 13 14 15 16
	 */
	public static void main(String[] args) {
		int[][] matrix = new int[][]{{2, 5}, {8, 4}, {0, -1}};
		printMatrixOneDirect(matrix);
		printMatrixGoThenBackDirect(matrix);
	}

	public static void printMatrixOneDirect(int[][] matrix) {
		int x = matrix[0].length;
		int y = matrix.length;

		for (int i = 0; i < x; i++) {
			int tmpY = 0;
			onePrint(i, tmpY, matrix);
			System.out.println();
		}

		for (int i = 1; i < y; i++) {
			int tmpX = x - 1;
			onePrint(tmpX, i, matrix);
			System.out.println();
		}
	}

	public static void onePrint(int tmpX, int tmpY, int[][] matrix) {
		while (tmpX >= 0 && tmpY <= (matrix.length - 1)) {
			System.out.print(matrix[tmpY][tmpX] + " ");
			tmpX = tmpX - 1;
			tmpY = tmpY + 1;
		}
	}

	public static void printMatrixGoThenBackDirect(int[][] matrix) {
		System.out.println(Arrays.toString(findDiagonalOrder(matrix)));
	}

	public static int[] findDiagonalOrder(int[][] matrix) {
		if (matrix == null || matrix.length == 0) return new int[0];

		int m = matrix.length;
		int n = matrix[0].length;
		int[] nums = new int[m * n];

		int k = 0;
		boolean bXFlag = true;
		for (int i = 0; i < m + n; i++) {
			int pm = bXFlag ? m : n;
			int pn = bXFlag ? n : m;

			int x = (i < pm) ? i : pm - 1;
			int y = i - x;

			while (x >= 0 && y < pn) {
				nums[k++] = bXFlag ? matrix[x][y] : matrix[y][x];
				x--;
				y++;
			}

			bXFlag = !bXFlag;
		}

		return nums;
	}
}