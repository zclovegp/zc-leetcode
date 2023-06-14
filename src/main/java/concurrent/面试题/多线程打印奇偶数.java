package concurrent.面试题;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaochong on 2023/5/21 21:31
 */
public class 多线程打印奇偶数 {

	private volatile static int curIndex = 0;

	public static void main(String[] args) throws InterruptedException {

		String targetStr = "11110182391789237189236178654715238612873123";
		char[] charArray = targetStr.toCharArray();
		ReentrantLock lock = new ReentrantLock();
		Condition con = lock.newCondition();

		Thread 奇数线程 = new Thread(() -> {
			lock.lock();
			while (curIndex < charArray.length) {
				try {
					if (charArray[curIndex] % 2 != 0) {
						System.out.println(Thread.currentThread().getName() + "charArray[curIndex]:" + charArray[curIndex]);
						curIndex++;
						// 把condition队列头节点挪到锁队列
						con.signal();
					} else {
						// 完全释放当前锁资源，unpark锁队列头节点，park自己
						con.await();
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			con.signal();
			lock.unlock();
		});
		奇数线程.setName("奇数线程!");

		Thread 偶数线程 = new Thread(() -> {
			lock.lock();
			while (curIndex < targetStr.length()) {
				try {
					if (charArray[curIndex] % 2 == 0) {
						System.out.println(Thread.currentThread().getName() + "charArray[curIndex]:" + charArray[curIndex]);
						curIndex++;
						con.signal();
					} else {
						con.await();
					}
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
			con.signal();
			lock.unlock();
		});
		偶数线程.setName("偶数线程!");

		奇数线程.start();
		偶数线程.start();
	}
}
