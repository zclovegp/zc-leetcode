package concurrent.thread.sync;

import com.google.common.collect.Lists;
import concurrent.ShowMeYourProcessor;

import java.util.List;

/**
 * 使用wait和notify需要先对Obj上锁，才能进行wait和notify处理，保证线程安全，但是不能指定唤起某一个线程
 *
 * @author zhaochong on 2023/5/7 21:38
 */
public class ObjWaitSync implements ShowMeYourProcessor {

	private static int flag = 0;

	@Override
	public List<Thread> getAll() {
		List<Thread> ts = Lists.newArrayList();
		Integer lockInt = 0;

		// 等待线程
		Thread t1 = new Thread(() -> {
			while (flag != 1) {
				// wait
				System.out.println("自旋中...");
				synchronized (lockInt) {// 会同步等待
					try {
						lockInt.wait();// 这就是一个断点，后续notify的也是从这里爬起来继续执行，wait后释放了锁
					} catch (InterruptedException e) {
						throw new RuntimeException(e);
					}
				}
			}
			System.out.println("状态扭转成功!");
		});
		ts.add(t1);

		// 修改状态线程
		Thread t2 = new Thread(() -> {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				throw new RuntimeException(e);
			}
			flag = 1;
			System.out.println(Thread.currentThread().getName() + "将状态修改为1!");
			synchronized (lockInt) {
				lockInt.notifyAll();// 唤起断点
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		});
		ts.add(t2);

		return ts;
	}
}
