package greed;

import java.util.Arrays;
import java.util.Stack;

/**
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 * <p>
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 * <p>
 *  
 * 示例 1:
 * <p>
 * 输入: g = [1,2,3], s = [1,1]
 * 输出: 1
 * 解释:
 * 你有三个孩子和两块小饼干，3个孩子的胃口值分别是：1,2,3。
 * 虽然你有两块小饼干，由于他们的尺寸都是1，你只能让胃口值是1的孩子满足。
 * 所以你应该输出1。
 * 示例 2:
 * <p>
 * 输入: g = [1,2], s = [1,2,3]
 * 输出: 2
 * 解释:
 * 你有两个孩子和三块小饼干，2个孩子的胃口值分别是1,2。
 * 你拥有的饼干数量和尺寸都足以让所有孩子满足。
 * 所以你应该输出2.
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= g.length <= 3 * 104
 * 0 <= s.length <= 3 * 104
 * 1 <= g[i], s[j] <= 231 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/assign-cookies
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/11 10:28
 */
public class EasyCookie {

	public static void main(String[] args) {
		System.out.println(findContentChildren(new int[]{1, 2, 3}, new int[]{1, 1}));
	}

	public static int findContentChildren(int[] childEatQtyArrays, int[] s) {
		if (childEatQtyArrays.length == 0 || s.length == 0) {
			return 0;
		}

		// 排序
		Arrays.sort(childEatQtyArrays);
		Arrays.sort(s);

		// 倒序入栈
		Stack<Integer> footStack = new Stack<>();
		for (int i = s.length - 1; i >= 0; i--) {
			footStack.push(s[i]);
		}

		// 贪心匹配
		int assignCount = 0;
		for (int i = 0; i < childEatQtyArrays.length; i++) {
			if (footStack.isEmpty()) {
				break;
			}
			for (int j = 0; j < 3000000; j++) {
				if (footStack.isEmpty()) {
					break;
				}
				Integer footSize = footStack.pop();
				if (childEatQtyArrays[i] <= footSize) {
					assignCount++;
					break;
				}
			}
		}

		return assignCount;
	}

	private static void sort(int[] a) {
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length - i - 1; j++) {
				if (a[j] > a[j + 1]) {
					int tmp = a[j + 1];
					a[j + 1] = a[j];
					a[j] = tmp;
				}
			}
		}
	}
}
