package 链表;

/**
 * @author zhaochong on 2023/1/5 21:32
 */
public class MediumLinkOddEvenSort {

	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(3);
		ListNode node3 = new ListNode(4);
		ListNode node4 = new ListNode(2);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		printLinkedTable(sortLinkedList(node1));
	}

	public static ListNode sortLinkedList(ListNode head) {

		// 拆分链表
		int i = 1;
		ListNode curNode = head;

		ListNode oNode = null;
		ListNode lastONode = null;

		ListNode jNode = null;
		ListNode lastJNode = null;

		while (curNode != null) {

			// 偶数
			if (i % 2 == 0) {
				if (oNode == null) {
					oNode = new ListNode(curNode.val);
					lastONode = oNode;
				} else {
					lastONode.next = new ListNode(curNode.val);
					lastONode = lastONode.next;
				}
			}
			// 奇数
			else {
				if (jNode == null) {
					jNode = new ListNode(curNode.val);
					lastJNode = jNode;
				} else {
					lastJNode.next = new ListNode(curNode.val);
					lastJNode = lastJNode.next;
				}
			}

			curNode = curNode.next;
			i++;
		}

		// 链表倒置
		ListNode reversedONode = reverse(oNode);

		// 双指针合并
		ListNode result = merge(reversedONode, jNode);

		return result;
	}

	public static ListNode reverse(ListNode root) {
		if (root == null) {
			return null;
		}

		ListNode preNode = null;
		ListNode curNode = root;
		while (curNode != null) {
			ListNode tmp = curNode.next;
			curNode.next = preNode;
			preNode = curNode;
			curNode = tmp;
		}

		return preNode;
	}

	public static ListNode merge(ListNode root1, ListNode root2) {
		ListNode index1 = root1;
		ListNode index2 = root2;

		ListNode result = null;
		ListNode lastResult = null;
		while (index1 != null || index2 != null) {

			if (index1 != null && index2 != null) {
				// 指针1小
				if (index1.val < index2.val) {
					if (result == null) {
						result = new ListNode(index1.val);
						lastResult = result;
					} else {
						lastResult.next = new ListNode(index1.val);
						lastResult = lastResult.next;
					}
					// 指针1向后移动
					index1 = index1.next;
				} else {
					if (result == null) {
						result = new ListNode(index2.val);
						lastResult = result;
					} else {
						lastResult.next = new ListNode(index2.val);
						lastResult = lastResult.next;
					}
					// 指针2向后移动
					index2 = index2.next;
				}
				continue;
			}

			if (index1 != null) {
				if (result == null) {
					result = new ListNode(index1.val);
					lastResult = result;
				} else {
					lastResult.next = new ListNode(index1.val);
					lastResult = lastResult.next;
				}
				// 指针1向后移动
				index1 = index1.next;
				continue;
			}

			if (index2 != null) {
				if (result == null) {
					result = new ListNode(index2.val);
					lastResult = result;
				} else {
					lastResult.next = new ListNode(index2.val);
					lastResult = lastResult.next;
				}
				// 指针2向后移动
				index2 = index2.next;
				continue;
			}
		}

		return result;
	}

	public static void printLinkedTable(ListNode root) {
		if (root == null) {
			System.out.print("null");
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
}
