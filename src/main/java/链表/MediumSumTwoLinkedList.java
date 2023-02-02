package 链表;

/**
 * @author zhaochong on 2023/1/12 08:29
 */
public class MediumSumTwoLinkedList {

	public static void main(String[] args) {
		ListNode head1 = new ListNode(1);
		ListNode head1Cur = head1;
		for (int i = 7; i < 10; i++) {
			head1Cur.next = new ListNode(i);
			head1Cur = head1Cur.next;
		}
		LinkedHelper.printLinkedTable(head1);

		ListNode head2 = new ListNode(9);
		ListNode head2Cur = head2;
		for (int i = 4; i < 9; i++) {
			head2Cur.next = new ListNode(9);
			head2Cur = head2Cur.next;
		}
		LinkedHelper.printLinkedTable(head2);
		ListNode h1 = LinkedHelper.reverse(head1);
		ListNode h2 = LinkedHelper.reverse(head2);

		LinkedHelper.printLinkedTable(LinkedHelper.reverse(addTwoNumbers(h1, h2)));
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode result = new ListNode();
		ListNode resultCur = result;

		boolean needUp = false;
		while (l1 != null && l2 != null) {

			SumResult sumResult = sumVal(l1.val, l2.val, needUp);
			needUp = sumResult.needUp;
			resultCur.next = new ListNode(sumResult.result);

			resultCur = resultCur.next;
			l1 = l1.next;
			l2 = l2.next;
		}

		while (l1 != null) {
			SumResult sumResult = sumVal(l1.val, 0, needUp);
			needUp = sumResult.needUp;
			resultCur.next = new ListNode(sumResult.result);
			l1 = l1.next;
			resultCur = resultCur.next;
		}

		while (l2 != null) {
			SumResult sumResult = sumVal(0, l2.val, needUp);
			needUp = sumResult.needUp;
			resultCur.next = new ListNode(sumResult.result);
			l2 = l2.next;
			resultCur = resultCur.next;
		}

		if (needUp){
			resultCur.next = new ListNode(1);
		}

		return result.next;
	}

	private static SumResult sumVal(int val1, int val2, boolean needUpdate) {
		int sum = val1 + val2;
		if (needUpdate) {
			sum += 1;
		}

		SumResult sumResult = new SumResult();
		if (sum > 9) {
			sumResult.needUp = true;
			sumResult.result = sum - 10;
		} else {
			sumResult.needUp = false;
			sumResult.result = sum;
		}
		return sumResult;
	}
}

class SumResult {

	public boolean needUp;
	public int result;
}
