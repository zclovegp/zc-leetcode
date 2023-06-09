package 二叉树;

import com.google.common.collect.Lists;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 1个节点1层 2的0次方
 * 3个节点2层
 * 7个节点3层
 *
 * @author zhaochong on 2023/6/8 21:42
 */
public class 层序遍历 {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		foreachByLevel(root);
	}

	public static void foreachByLevel(TreeNode root) {
		if (root == null) {
			return;
		}

		List<TreeNode> sameLevelNodes = Lists.newArrayList();
		sameLevelNodes.add(root);

		while (!CollectionUtils.isEmpty(sameLevelNodes)) {

			List<TreeNode> nextNodes = Lists.newArrayList();
			for (TreeNode sameLevelNode : sameLevelNodes) {
				System.out.println(sameLevelNode.val);
				if (sameLevelNode.left != null) {
					nextNodes.add(sameLevelNode.left);
				}
				if (sameLevelNode.right != null) {
					nextNodes.add(sameLevelNode.right);
				}
			}

			sameLevelNodes = nextNodes;
		}
	}
}
