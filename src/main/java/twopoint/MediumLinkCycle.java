package twopoint;

import java.util.Objects;

/**
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * <p>
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * <p>
 * 不允许修改 链表。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/linked-list-cycle-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/16 22:20
 */
public class MediumLinkCycle {

	public static void main(String[] args) {
//		ListNode x3 = new ListNode(3);
//		ListNode x2 = new ListNode(2);
//		ListNode x0 = new ListNode(0);
//		ListNode x4 = new ListNode(4);
//		x3.next = x2;
//		x2.next = x0;
//		x4.next = x2;

		ListNode x1 = new ListNode(1);
		ListNode x2 = new ListNode(2);
		x1.next = x2;
		x2.next = x1;

		ListNode listNode = detectCycle(x1);
		System.out.println(listNode.val);
	}

	public static ListNode detectCycle(ListNode head) {
		if (head == null) {
			return null;
		}

		ListNode fastNode = head;
		ListNode slowNode = head;

		while (true) {

			// 寻找下一个快指针
			fastNode = getNextNode(fastNode, 2);
			if (fastNode == null) {
				System.out.println("链表无环");
				return null;
			}

			// 寻找下一个慢指针
			slowNode = getNextNode(slowNode, 1);

			// 快指针追到慢指针（证明链表成环）
			if (Objects.equals(fastNode, slowNode)) {
				fastNode = head;
				while (true) {
					if (Objects.equals(fastNode, slowNode)) {
						return fastNode;
					}
					fastNode = getNextNode(fastNode, 1);
					slowNode = getNextNode(slowNode, 1);
				}
			}
		}
	}

	private static ListNode getNextNode(ListNode node, int step) {
		if (node == null || step <= 0) {
			return null;
		}

		ListNode targetNode = node;
		for (int i = 0; i < step; i++) {
			if (targetNode == null) {
				return null;
			}
			targetNode = targetNode.next;
		}

		return targetNode;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}
