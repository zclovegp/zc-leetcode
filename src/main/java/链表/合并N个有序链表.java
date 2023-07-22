package 链表;

import java.util.ArrayList;
import java.util.Comparator;
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
		ListNodeForHere root1 = new ListNodeForHere(1);
		root1.next = new ListNodeForHere(2);
		root1.next.next = new ListNodeForHere(3);

		ListNodeForHere root2 = new ListNodeForHere(4);
		root2.next = new ListNodeForHere(5);
		root2.next.next = new ListNodeForHere(6);
		root2.next.next.next = new ListNodeForHere(7);

		List<ListNodeForHere> input = new ArrayList<>();
		input.add(root1);
		input.add(root2);

		printNode(mergeKLists(input));
	}

	public static ListNodeForHere mergeKLists(List<ListNodeForHere> lists) {
		// 初始化每个链表的头节点
		Map<Integer, ListNodeForHere> indexWithCurNode = new HashMap<>();
		for (int i = 0; i < lists.size(); i++) {
			indexWithCurNode.put(i, lists.get(i));
		}

		ListNodeForHere resultCurNode = new ListNodeForHere(999);
		ListNodeForHere head = resultCurNode;
		while (indexWithCurNode.values().stream().anyMatch(Objects::nonNull)) {

			// 使用优先级队列
			PriorityQueue<ListNodeForHere> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));

			// 扔到优先级队列
			for (int i = 0; i < lists.size(); i++) {
				ListNodeForHere indexCurNode = indexWithCurNode.get(i);
				if (indexCurNode != null) {
					queue.add(indexCurNode);
				}
			}

			ListNodeForHere first = queue.peek();
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

	public static void printNode(ListNodeForHere node) {
		if (node == null) {
			return;
		}

		System.out.println(node.val);
		printNode(node.next);
	}
}

class ListNodeForHere {
	int val;
	ListNodeForHere next = null;

	public ListNodeForHere(int val) {
		this.val = val;
	}
}
