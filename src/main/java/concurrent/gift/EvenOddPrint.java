package concurrent.gift;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaochong on 2023/5/21 21:31
 */
public class EvenOddPrint {

	private static int curIndex = 0;

	public static void main(String[] args) {

		char[] charArray = "12345678901234567890123456789012345678901234567890".toCharArray();
		int maxIndex = charArray.length - 1;

		ReentrantLock lock = new ReentrantLock();
		Condition con = lock.newCondition();

		// 奇数线程
		Thread odd = new Thread(() -> {
			process(lock, new ConditionBoolean() {
				@Override
				public boolean match(char c) {
					int count = Integer.parseInt(String.valueOf(c));
					return count % 2 != 0;
				}
			}, maxIndex, charArray, con);
		});
		odd.setName("奇数线程");

		// 偶数线程
		Thread even = new Thread(() -> {
			process(lock, new ConditionBoolean() {
				@Override
				public boolean match(char c) {
					int count = Integer.parseInt(String.valueOf(c));
					return count % 2 == 0;
				}
			}, maxIndex, charArray, con);
		});
		even.setName("偶数线程");

		odd.start();
		even.start();
	}

	public static void process(ReentrantLock lock, ConditionBoolean conditionBoolean, int maxIndex, char[] charArray, Condition con) {
		while (true) {
			// 加锁
			lock.lock();
			System.out.println("当前抢到线程锁的线程叫" + Thread.currentThread().getName());
			try {
				if (curIndex <= maxIndex) {
					char curChar = charArray[curIndex];
					if (conditionBoolean.match(curChar)) {
						System.out.println(Thread.currentThread() + "打印" + curChar);
						curIndex = curIndex + 1;
						con.signal();
					}
					// 不满足下次就别抢了，等着吧
					else {
						System.out.println("线程" + Thread.currentThread().getName() + "这个锁白强了...等待吧");
						con.await();
					}
				} else {
					break;
				}
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} finally {
				// 解锁
				lock.unlock();
			}
		}
	}
}

interface ConditionBoolean {
	boolean match(char c);
}
