package threadlocal;

import org.apache.commons.lang3.RandomUtils;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaochong on 2022/12/3 11:45
 */
public class ThreadLocalDemo {

	private static final ThreadLocal<Integer> TL = new ThreadLocal<>();

	private static final ThreadLocal<Integer> TL2 = new ThreadLocal<>();

	public static void main(String[] args) {

		TL.set(10086);
		TL2.set(10010);

		TL.get();
		TL2.get();

		TL.remove();
		TL2.remove();

		ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(3, 5, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100));

		for (int i = 0; i < 100; i++) {
			Runnable runnable = () -> {
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					System.out.println("中断异常");
				}
				int random = RandomUtils.nextInt(500, 999);
				TL.set(random);
				TL2.set(random);

				System.out.println("Thread --> " + Thread.currentThread().getName() + "，TL的random：" + TL.get());
				System.out.println("Thread --> " + Thread.currentThread().getName() + "，TL2的random：" + TL2.get());

				TL.remove();
				TL2.remove();
			};
			poolExecutor.submit(runnable);
		}

		System.out.println("ActiveCount ===> " + poolExecutor.getActiveCount());
		System.out.println("CorePoolSize ===> " + poolExecutor.getCorePoolSize());
		System.out.println("MaximumPoolSize ===> " + poolExecutor.getMaximumPoolSize());
	}
}
