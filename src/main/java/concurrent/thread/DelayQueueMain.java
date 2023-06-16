package concurrent.thread;

import org.apache.commons.lang3.time.DateUtils;

import java.util.Date;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaochong on 2023/6/15 15:26
 */
public class DelayQueueMain {

	private static final DelayQueue<DelayedBo> DELAY_QUEUE = new DelayQueue<>();

	public static void main(String[] args) {

		Date curDate = new Date();

		DelayedBo delayedBo = new DelayedBo();
		delayedBo.expectTakeTime = DateUtils.addSeconds(curDate, 5);
		System.out.println(delayedBo.getDelay(TimeUnit.SECONDS));

		DelayedBo delayedBo2 = new DelayedBo();
		delayedBo2.expectTakeTime = DateUtils.addSeconds(curDate, 10);
		System.out.println(delayedBo2.getDelay(TimeUnit.SECONDS));

		DelayedBo delayedBo3 = new DelayedBo();
		delayedBo3.expectTakeTime = DateUtils.addSeconds(curDate, 15);
		System.out.println(delayedBo3.getDelay(TimeUnit.SECONDS));

		DELAY_QUEUE.offer(delayedBo);
		DELAY_QUEUE.offer(delayedBo2);
		DELAY_QUEUE.offer(delayedBo3);

		// 消费线程
		for (int i = 0; i < 2; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						while (!DELAY_QUEUE.isEmpty()) {
							DelayedBo bo = DELAY_QUEUE.take();
							System.out.println(Thread.currentThread().getName() + "读取了元素" + bo);
						}
					} catch (InterruptedException e) {

					}
				}
			}, "线程" + i).start();
		}
	}
}

class DelayedBo implements Delayed {

	public Date expectTakeTime;

	@Override
	public long getDelay(TimeUnit unit) {
		TimeUnit second = TimeUnit.SECONDS;
		return second.convert(expectTakeTime.getTime() - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
	}

	@Override
	public int compareTo(Delayed o) {
		boolean bigger = (this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS)) > 0;
		if (bigger) {
			return 1;
		}

		boolean equals = (this.getDelay(TimeUnit.SECONDS) - o.getDelay(TimeUnit.SECONDS)) == 0;
		if (equals) {
			return 0;
		}

		return -1;
	}

	@Override
	public String toString() {
		return expectTakeTime + "";
	}
}
