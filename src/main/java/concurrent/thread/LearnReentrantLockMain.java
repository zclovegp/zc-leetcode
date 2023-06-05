package concurrent.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaochong on 2023/5/8 16:57
 */
public class LearnReentrantLockMain {

	public static void main(String[] args) throws InterruptedException {
		ReentrantLock reentrantLock = new ReentrantLock();
		Condition c1 = reentrantLock.newCondition();

		for (int i = 0; i < 5; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					while (true) {

						try {
							reentrantLock.lock();
							reentrantLock.lock();
							System.out.println(Thread.currentThread().getName() + "===：加锁成功!");
							System.out.println(Thread.currentThread().getName() + "===：holdCount:" + reentrantLock.getHoldCount());
							Thread.sleep(3000);
							System.out.println(Thread.currentThread().getName() + "===：开始执行await");
							c1.await();
							System.out.println(Thread.currentThread().getName() + "===：开始恢复继续执行!!!");
						} catch (InterruptedException e) {
							throw new RuntimeException(e);
						} finally {
							System.out.println(Thread.currentThread().getName() + "===：执行final!");
						}
					}
				}
			}).start();
		}

		Thread.sleep(20000);
		System.out.println(Thread.currentThread().getName() + "===：holdCount:" + reentrantLock.getHoldCount());
	}
}
