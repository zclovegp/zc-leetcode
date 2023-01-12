package 链表;

import java.util.Stack;

/**
 * @author zhaochong on 2023/1/12 08:29
 */
public class MediumSumTwoLinkedList {

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		Stack<Integer> s1 = new Stack<>();
		Stack<Integer> s2 = new Stack<>();
		putNodeToStack(l1, s1);
		putNodeToStack(l2, s2);

		Stack<Integer> result = new Stack<>();
		boolean upBit = false;
		while (!s1.isEmpty() || !s2.isEmpty()) {

			if (!s1.isEmpty() && !s2.isEmpty()) {
				Integer value1 = s1.pop();
				Integer value2 = s2.pop();
				upBit = sumThenPushStack(result, upBit, value1 + value2);
				continue;
			}

			if (!s1.isEmpty()) {
				upBit = sumThenPushStack(result, upBit, s1.pop());
				continue;
			}

			if (!s2.isEmpty()) {
				upBit = sumThenPushStack(result, upBit, s2.pop());
				continue;
			}
		}

		if (upBit) {
			result.push(1);
		}

		ListNode resultNode = null;
		ListNode lastNode = null;
		while (!result.isEmpty()) {
			Integer val = result.pop();
			if (lastNode == null) {
				resultNode = new ListNode(val);
				lastNode = resultNode;
			} else {
				lastNode.next = new ListNode(val);
				lastNode = lastNode.next;
			}
		}

		return resultNode;
	}

	private static boolean sumThenPushStack(Stack<Integer> result, boolean upBit, Integer value) {
		// 进位+1
		if (upBit) {
			value = value + 1;
		}
		// 需要标记进位
		if (value >= 10) {
			upBit = true;
			result.push(value - 10);
		} else {
			upBit = false;
			result.push(value);
		}
		return upBit;
	}

	public static void putNodeToStack(ListNode listNode, Stack<Integer> stack) {
		ListNode curNode = listNode;
		while (curNode != null) {
			stack.push(curNode.val);
			curNode = curNode.next;
		}
	}
}
