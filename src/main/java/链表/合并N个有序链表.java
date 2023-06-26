package 链表;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author zhaochong on 2023/6/26 15:23
 */
public class 合并N个有序链表 {

	public static void main(String[] args) {
		ListNodeComparable root1 = new ListNodeComparable(1);
		root1.next = new ListNodeComparable(2);
		root1.next.next = new ListNodeComparable(3);

		ListNodeComparable root2 = new ListNodeComparable(4);
		root2.next = new ListNodeComparable(5);
		root2.next.next = new ListNodeComparable(6);
		root2.next.next.next = new ListNodeComparable(7);

		List<ListNodeComparable> input = new ArrayList<>();
		input.add(root1);
		input.add(root2);

		printNode(mergeKLists(input));
	}

	public static ListNodeComparable mergeKLists(List<ListNodeComparable> lists) {
		// 初始化每个链表的头节点
		Map<Integer, ListNodeComparable> indexWithCurNode = new HashMap<>();
		for (int i = 0; i < lists.size(); i++) {
			indexWithCurNode.put(i, lists.get(i));
		}

		ListNodeComparable resultCurNode = new ListNodeComparable(999);
		ListNodeComparable head = resultCurNode;
		while (indexWithCurNode.values().stream().anyMatch(Objects::nonNull)) {
			PriorityQueue<ListNodeComparable> queue = new PriorityQueue<>();
			// 扔到优先级队列
			for (int i = 0; i < lists.size(); i++) {
				ListNodeComparable indexCurNode = indexWithCurNode.get(i);
				if (indexCurNode != null) {
					queue.add(indexCurNode);
				}
			}

			ListNodeComparable first = queue.peek();
			if (first == null) {
				break;
			}
			AtomicReference<Integer> index = new AtomicReference<>(0);
			indexWithCurNode.forEach((k, v) -> {
				if (v == first) {
					index.set(k);
				}
			});

			// 最小指针向后移动
			indexWithCurNode.put(index.get(), first.next);

			// 结果指针向后移动
			resultCurNode.next = first;
			resultCurNode = resultCurNode.next;
		}

		return head.next;
	}

	public static void printNode(ListNodeComparable node) {
		if (node == null) {
			return;
		}

		System.out.println(node.val);
		printNode(node.next);
	}
}

class ListNodeComparable implements Comparable<ListNodeComparable> {
	int val;
	ListNodeComparable next = null;

	public ListNodeComparable(int val) {
		this.val = val;
	}

	@Override
	public int compareTo(ListNodeComparable o) {
		return Integer.compare(this.val, o.val);
	}
}
