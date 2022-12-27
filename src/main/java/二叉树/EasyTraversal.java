package 二叉树;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 迭代的通用写法
 * <p>
 * while (!stack.isNotEmpty || curRoot != null) {
 * <p>
 * while (curRoot.left != null) {
 * // 压栈
 * // 如果是前序在这里addResult
 * }
 * <p>
 * // 如果是前序和中序，这里可以stack.pop()
 * // 如果是中序在这里addResult
 * <p>
 * // 如果是后序在这里stack.peek()，需要判定是否是从下往上返回的节点，如果是才能addResult，并出栈
 * <p>
 * }
 *
 * @author zhaochong on 2022/12/27 07:00
 */
public class EasyTraversal {

	public List<Integer> preorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<>();
		preorderTraversalV2(root, result);
		return result;
	}

	public void preorderTraversalV1(TreeNode root, List<Integer> result) {
		if (root == null) {
			return;
		}
		result.add(root.val);
		preorderTraversalV1(root.left, result);
		preorderTraversalV1(root.right, result);
	}

	public void postorderTraversalV1(TreeNode root, List<Integer> result) {
		if (root == null) {
			return;
		}
		postorderTraversalV1(root.left, result);
		postorderTraversalV1(root.right, result);
		result.add(root.val);
	}

	/**
	 * 前序
	 */
	public void preorderTraversalV2(TreeNode root, List<Integer> result) {
		if (root == null) {
			return;
		}
		TreeNode currentRoot = root;
		Stack<TreeNode> stack = new Stack<>();

		while (!stack.isEmpty() || currentRoot != null) {

			// 根 -> 左 -> 右
			while (currentRoot != null) {
				stack.push(currentRoot);
				result.add(currentRoot.val);
				currentRoot = currentRoot.left;
			}

			currentRoot = stack.pop();
			currentRoot = currentRoot.right;
		}
	}

	/**
	 * 中序
	 */
	public void inorderTraversalV2(TreeNode root, List<Integer> result) {
		if (root == null) {
			return;
		}

		TreeNode cur = root;
		Stack<TreeNode> stack = new Stack<>();

		while (cur != null || !stack.isEmpty()) {

			// 左 -> 根 -> 右
			while (cur != null) {
				stack.push(cur);
				cur = cur.left;
			}
			cur = stack.pop();
			result.add(cur.val);
			cur = cur.right;
		}
	}

	/**
	 * 后序
	 */
	public void postorderTraversalV2(TreeNode root, List<Integer> result) {
		if (root == null) {
			return;
		}

		TreeNode lastRoot = null;
		TreeNode currentRoot = root;
		Stack<TreeNode> stack = new Stack<>();
		while (!stack.isEmpty() || currentRoot != null) {

			// 左 -> 右 -> 根
			while (currentRoot != null) {
				stack.push(currentRoot);
				currentRoot = currentRoot.left;
			}

			// 左子树遍历完成，不要出栈，开始右子树
			currentRoot = stack.peek();

			// 左右子树都处理完，需要把currentRoot=null（不能再遍历了）
			if (currentRoot.right == null || currentRoot.right == lastRoot) {
				stack.pop();
				result.add(currentRoot.val);
				lastRoot = currentRoot;
				currentRoot = null;
			} else {
				currentRoot = currentRoot.right;
			}
		}
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode() {
	}

	TreeNode(int val) {
		this.val = val;
	}

	TreeNode(int val, TreeNode left, TreeNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}
