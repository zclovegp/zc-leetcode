package 链表;

/**
 * @author zhaochong on 2023/2/2 22:46
 */
public class LinkedHelper {

	public static ListNode reverse(ListNode head) {
		ListNode preNode = null;
		while (head != null) {
			ListNode curNextNode = head.next;
			head.next = preNode;
			preNode = head;
			head = curNextNode;
		}
		return preNode;
	}

	public static void printLinkedTable(ListNode root) {
		if (root == null) {
			System.out.print("null");
			System.out.println();
			return;
		}
		System.out.print(root.val + "->");
		printLinkedTable(root.next);
	}
}

class ListNode {
	int val;
	ListNode next = null;

	public ListNode(int val) {
		this.val = val;
	}

	public ListNode() {
	}
}
