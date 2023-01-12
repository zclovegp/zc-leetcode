package 二叉树;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * @author zhaochong on 2023/1/12 07:52
 */
public class EasyLowestCommonAncestor {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		// 遍历二叉树，构建结点父结点map
		Map<TreeNode, TreeNode> nodeWithParentNode = new HashMap<>();
		Stack<TreeNode> stack = new Stack<>();
		TreeNode curNode = root;

		while (curNode != null || !stack.isEmpty()) {
			while (curNode != null) {
				if (curNode.left != null) {
					nodeWithParentNode.put(curNode.left, curNode);
				}
				stack.push(curNode);
				curNode = curNode.left;
			}

			// 不存在左子树，出栈
			curNode = stack.pop();
			if (curNode != null && curNode.right != null) {
				nodeWithParentNode.put(curNode.right, curNode);
			}
			curNode = curNode.right;
		}

		// p的所有祖宗（包含自己）
		List<TreeNode> pParents = new ArrayList<>();
		TreeNode curParentP = p;
		// 边界情况，自己就是祖宗节点也需要考虑
		if (curParentP != null) {
			pParents.add(curParentP);
		}
		while (true) {
			curParentP = nodeWithParentNode.get(curParentP);
			if (curParentP == null) {
				break;
			}
			pParents.add(curParentP);
		}

		// 遍历q的所有祖宗
		TreeNode curParentQ = q;
		while (true) {
			// 边界情况，自己就是祖宗节点也需要考虑
			if (pParents.contains(curParentQ)) {
				return curParentQ;
			}
			curParentQ = nodeWithParentNode.get(curParentQ);
			if (curParentQ == null) {
				break;
			}

			if (pParents.contains(curParentQ)) {
				return curParentQ;
			}
		}

		return null;
	}

	public static void main(String[] args) {
		Stack<Integer> s1 = new Stack<>();
		System.out.println(s1.pop());
	}
}
