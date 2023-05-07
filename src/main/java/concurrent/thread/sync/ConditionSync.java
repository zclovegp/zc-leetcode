package concurrent.thread.sync;

import com.google.common.collect.ImmutableList;
import concurrent.ShowMeYourProcessor;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaochong on 2023/5/7 21:39
 */
public class ConditionSync implements ShowMeYourProcessor {

	private static int flag = 0;

	@Override
	public List<Thread> getAll() {
		ReentrantLock rl = new ReentrantLock();
		Condition condition1 = rl.newCondition();

		Thread t1 = new Thread(() -> {
			while (flag != 1) {
				rl.lock();
				try {
					System.out.println("开始自旋等待...");
					condition1.await();
					System.out.println("被重新激活!");
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				} finally {
					rl.unlock();
				}
			}
			System.out.println("状态成功扭转!");
		});

		Thread t2 = new Thread(() -> {
			rl.lock();
			try {
				Thread.sleep(3000);
				flag = 1;
				condition1.signalAll();
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			} finally {
				rl.unlock();
			}
		});

		return ImmutableList.of(t1, t2);
	}
}
