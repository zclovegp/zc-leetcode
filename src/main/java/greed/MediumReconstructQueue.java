package greed;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 假设有打乱顺序的一群人站成一个队列，数组 people 表示队列中一些人的属性（不一定按顺序）。每个 people[i] = [hi, ki] 表示第 i 个人的身高为 hi ，前面 正好 有 ki 个身高大于或等于 hi 的人。
 * <p>
 * 请你重新构造并返回输入数组 people 所表示的队列。返回的队列应该格式化为数组 queue ，其中 queue[j] = [hj, kj] 是队列中第 j 个人的属性（queue[0] 是排在队列前面的人）。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]
 * 输出：[[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 * 解释：
 * 编号为 0 的人身高为 5 ，没有身高更高或者相同的人排在他前面。
 * 编号为 1 的人身高为 7 ，没有身高更高或者相同的人排在他前面。
 * 编号为 2 的人身高为 5 ，有 2 个身高更高或者相同的人排在他前面，即编号为 0 和 1 的人。
 * 编号为 3 的人身高为 6 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 编号为 4 的人身高为 4 ，有 4 个身高更高或者相同的人排在他前面，即编号为 0、1、2、3 的人。
 * 编号为 5 的人身高为 7 ，有 1 个身高更高或者相同的人排在他前面，即编号为 1 的人。
 * 因此 [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]] 是重新构造后的队列。
 * 示例 2：
 * <p>
 * 输入：people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]
 * 输出：[[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= people.length <= 2000
 * 0 <= hi <= 106
 * 0 <= ki < people.length
 * 题目数据确保队列可以被重建
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/queue-reconstruction-by-height
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @author zhaochong on 2022/8/21 11:48
 */
public class MediumReconstructQueue {

	public static void main(String[] args) {
		reconstructQueue(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}});
	}

	public static int[][] reconstructQueue(int[][] people) {

		List<People> peoples = Arrays.stream(people)
				.map(x -> {
					People p = new People();
					p.setHeight(x[0]);
					p.setBeforeHighCount(x[1]);
					return p;
				})
				.sorted(Comparator.comparing(People::getHeight, Comparator.reverseOrder())
						.thenComparing(People::getBeforeHighCount))
				.collect(Collectors.toList());

		for (int i = 0; i < peoples.size(); i++) {

			People p = peoples.get(i);

			Integer beforeHighCount = p.getBeforeHighCount();
			int curBeforeHighCount = i;

			for (int j = i; j > 0; j--) {
				// 理论高度 < 实际高度 ，跟前面元素调换顺序
				if (beforeHighCount < curBeforeHighCount) {
					People before = peoples.get(j - 1);
					peoples.set(j - 1, p);
					peoples.set(j, before);
					curBeforeHighCount--;
				}
			}
		}

		return peoples.stream()
				.map(x -> new int[]{x.getHeight(), x.getBeforeHighCount()})
				.collect(Collectors.toList())
				.toArray(new int[0][0]);
	}

	static class People {

		private Integer height;

		private Integer beforeHighCount;

		public Integer getHeight() {
			return height;
		}

		public void setHeight(Integer height) {
			this.height = height;
		}

		public Integer getBeforeHighCount() {
			return beforeHighCount;
		}

		public void setBeforeHighCount(Integer beforeHighCount) {
			this.beforeHighCount = beforeHighCount;
		}
	}
}
