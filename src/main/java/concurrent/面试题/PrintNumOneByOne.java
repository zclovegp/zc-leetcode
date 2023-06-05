package concurrent.面试题;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印1～100的自然数
 * <p>
 * 注意：
 * 1、await会释放锁， 后续被signal后会重新获得锁，所以不要把lock写到循环内，以免造成重入锁累加
 *
 * @author zhaochong on 2023/6/1 11:58
 */
public class PrintNumOneByOne {

	private static volatile int num = 1;
	private static final ReentrantLock LOCK = new ReentrantLock();
	private static final Condition CON = LOCK.newCondition();
	private static final CountDownLatch CD = new CountDownLatch(5);


	public static void main(String[] args) throws InterruptedException {

		for (int i = 0; i < 5; i++) {
			Thread t = new Thread(() -> {
				try {
					// 先上锁
					LOCK.lock();

					while (num <= 100) {
						System.out.println(Thread.currentThread().getName() + "打印数字:" + num + ", holdCount:" + LOCK.getHoldCount());
						num++;
						CON.signal();
						CON.await();
					}

				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				} finally {
					CON.signal();
					LOCK.unlock();
				}

				System.out.println(Thread.currentThread().getName() + "跳出循环前唤起其他线程");
				CD.countDown();
			});

			t.start();
		}

		CD.await();
	}
}
