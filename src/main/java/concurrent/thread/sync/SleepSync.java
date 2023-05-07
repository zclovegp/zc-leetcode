package concurrent.thread.sync;

import com.google.common.collect.Lists;
import concurrent.ShowMeYourProcessor;

import java.util.List;

/**
 * 直接自旋对cpu消耗过多，sleep对结果获取及时性不够
 *
 * @author zhaochong on 2023/5/7 21:38
 */
public class SleepSync implements ShowMeYourProcessor {

	private static int flag = 0;

	@Override
	public List<Thread> getAll() {


		List<Thread> ts = Lists.newArrayList();

		// 等待线程
		Thread t1 = new Thread(() -> {
			while (flag != 1) {
				// wait
				System.out.println("自旋中...");
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
		});
		ts.add(t2);

		return ts;
	}
}
