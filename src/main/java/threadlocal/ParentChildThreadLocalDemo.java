package threadlocal;

/**
 * @author zhaochong on 2023/7/17 11:37
 */
public class ParentChildThreadLocalDemo {

	private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

	public static void main(String[] args) throws InterruptedException {
		// 父线程设置ThreadLocal变量的值
		threadLocal.set("Hello, ThreadLocal!");

		// 创建子线程
		Thread childThread = new Thread(() -> {
			while (true) {
				// 子线程获取父线程中的ThreadLocal变量的值
				String value = threadLocal.get();
				threadLocal.set("我重新定义了");
				System.out.println("子线程获取到的值：" + value);
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		});

		// 启动子线程
		childThread.start();

		Thread.sleep(5000);
		System.out.println(Thread.currentThread().getName() + threadLocal.get());
	}
}
