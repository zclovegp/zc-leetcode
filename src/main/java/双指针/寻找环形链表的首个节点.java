package 双指针;

/**
 * 快慢指针
 *
 * @author zhaochong on 2023/7/30 21:27
 */
public class 寻找环形链表的首个节点 {

	public static void main(String[] args) {

		ListNode root1 = new ListNode(1);
		ListNode root2 = new ListNode(2);
		root1.next = root2;
		root2.next = root1;

		System.out.println(findFirstCycle(root1).val);
	}

	public static ListNode findFirstCycle(ListNode root) {

		if (root == null) {
			return null;
		}

		if (root.next == null) {
			return null;
		}

		ListNode fast = root;
		ListNode slow = root;

		ListNode meetNode;
		while (true) {

			fast = getNextThenNext(fast);
			slow = getNext(slow);

			if (fast == null || slow == null) {
				return null;
			}

			if (fast == slow) {
				meetNode = fast;
				break;
			}
		}

		slow = root;
		while (true) {

			if (slow == meetNode) {
				return meetNode;
			}

			meetNode = getNext(meetNode);
			slow = getNext(slow);
		}
	}

	private static ListNode getNext(ListNode node) {
		if (node == null) {
			return null;
		}

		if (node.next == null) {
			return null;
		}

		return node.next;
	}

	private static ListNode getNextThenNext(ListNode node) {
		if (node == null) {
			return null;
		}

		if (node.next == null) {
			return null;
		}

		if (node.next.next == null) {
			return null;
		}

		return node.next.next;
	}
}
