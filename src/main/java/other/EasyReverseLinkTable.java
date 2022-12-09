package other;

/**
 * @author zhaochong on 2022/12/1 22:32
 */
public class EasyReverseLinkTable {

	public static ListNode reverseList(ListNode head) {
		if (head == null) {
			return null;
		}

		if (head.next == null) {
			return head;
		}

		ListNode pre = null;
		ListNode current = head;

		while (current != null) {
			ListNode next = current.next;
			current.next = pre;
			pre = current;
			current = next;
		}
		return pre;
	}

	public static void main(String[] args) {
		ListNode node = initListNode();
		printNode(node);

		ListNode head = reverseList(node);
		printNode(head);
	}

	private static void printNode(ListNode node) {
		ListNode current = node;
		while (current != null) {
			System.out.println("这是:" + current.val);
			current = current.next;
		}
	}

	private static ListNode initListNode() {
		ListNode node1 = new ListNode(1, null);
		ListNode node2 = new ListNode(2, node1);
		ListNode node3 = new ListNode(3, node2);
		ListNode node4 = new ListNode(4, node3);
		return new ListNode(5, node4);
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode() {
	}

	ListNode(int val) {
		this.val = val;
	}

	ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
