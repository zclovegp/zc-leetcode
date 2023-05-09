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

		Thread t1 = new Thread(() -> {
			reentrantLock.lock();
			try {
				c1.await();
				System.out.println("t1被唤醒了");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} finally {
				reentrantLock.unlock();
			}
		});

		Thread t2 = new Thread(() -> {
			reentrantLock.lock();
			try {
				c1.await();
				System.out.println("t2被唤醒了");
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} finally {
				reentrantLock.unlock();
			}
		});

		t1.start();
		t2.start();

		Thread.sleep(3000);

		reentrantLock.lock();
		reentrantLock.lock();
		reentrantLock.lock();

		System.out.println("待执行");
	}
}
