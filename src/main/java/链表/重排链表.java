package 链表;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaochong on 2023/7/31 16:42
 */
public class 重排链表 {

	public static void main(String[] args) {
		ListNode n1 = new ListNode(1);
		ListNode n2 = new ListNode(2);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(4);
		n1.next = n2;
		n2.next = n3;
		n3.next = n4;

		LinkedHelper.printLinkedTable(n1);
		LinkedHelper.printLinkedTable(reorderList(n1));
	}

	public static ListNode reorderList(ListNode head) {
		List<ListNode> result = getList(head);
		if (result.size() <= 1) {
			return head;
		}

		return sortListThenReturnHead(result);
	}

	private static ListNode sortListThenReturnHead(List<ListNode> result) {
		int startIndex = 0;
		int endIndex = result.size() - 1;
		ListNode newNode = new ListNode();
		ListNode lastNode = newNode;

		while (startIndex <= endIndex) {

			ListNode node1;
			if (startIndex != endIndex) {
				node1 = result.get(startIndex);
				ListNode node2 = result.get(endIndex);
				lastNode.next = node1;
				node1.next = node2;
				// lastNode前进
				lastNode = node2;
			} else {
				node1 = result.get(startIndex);
				lastNode.next = node1;
				// lastNode前进
				lastNode = node1;
			}

			startIndex++;
			endIndex--;
		}

		lastNode.next = null;
		return newNode.next;
	}

	public static List<ListNode> getList(ListNode node) {
		List<ListNode> result = new ArrayList<>();
		ListNode curNode = node;
		while (curNode != null) {
			result.add(curNode);
			curNode = curNode.next;
		}

		return result;
	}
}
