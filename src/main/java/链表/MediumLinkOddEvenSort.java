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
		LinkedHelper.printLinkedTable(sortLinkedList(node1));
	}

	public static ListNode sortLinkedList(ListNode head) {
		// 拆分
		ListNode[] divided = divide(head);
		ListNode jHead = divided[0];
		ListNode oHead = divided[1];
		// 链表倒置
		ListNode reversedONode = LinkedHelper.reverse(oHead);
		// 双指针合并
		return mergeV2(reversedONode, jHead);
	}

	public static ListNode[] divide(ListNode head) {
		ListNode jHead = new ListNode();
		ListNode oHead = new ListNode();
		int i = 1;
		ListNode jCur = jHead;
		ListNode oCur = oHead;
		while (head != null) {
			// 偶
			if (i % 2 == 0) {
				oCur.next = head;
				oCur = oCur.next;
			} else {// 奇
				jCur.next = head;
				jCur = jCur.next;
			}
			head = head.next;
			i++;
		}
		// 收尾
		jCur.next = null;
		oCur.next = null;
		return new ListNode[]{jHead.next, oHead.next};
	}

	public static ListNode mergeV2(ListNode leftHead, ListNode rightHead) {
		ListNode result = new ListNode();
		ListNode curResult = result;
		while (leftHead != null || rightHead != null) {
			ListNode littleNode = getLittleNode(leftHead, rightHead);
			if (littleNode == null) {
				break;
			}

			if (leftHead == littleNode) {
				leftHead = leftHead.next;
			}

			if (rightHead == littleNode) {
				rightHead = rightHead.next;
			}

			curResult.next = littleNode;
			curResult = curResult.next;
		}
		return result.next;
	}

	public static ListNode getLittleNode(ListNode node1, ListNode node2) {
		if (node1 == null && node2 == null) {
			return null;
		}
		if (node1 == null) {
			return node2;
		}
		if (node2 == null) {
			return node1;
		}
		if (node1.val < node2.val) {
			return node1;
		} else {
			return node2;
		}
	}

	public static ListNode merge(ListNode leftHead, ListNode rightHead) {
		ListNode result = new ListNode();
		ListNode resultCur = result;
		while (leftHead != null && rightHead != null) {
			if (leftHead.val < rightHead.val) {
				resultCur.next = leftHead;
				leftHead = leftHead.next;
			} else {
				resultCur.next = rightHead;
				rightHead = rightHead.next;
			}
			resultCur = resultCur.next;
		}
		if (leftHead != null) {
			resultCur.next = leftHead;
		}
		if (rightHead != null) {
			resultCur.next = rightHead;
		}
		return result.next;
	}
}
