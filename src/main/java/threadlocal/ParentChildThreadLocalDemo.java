package threadlocal;

/**
 * @author zhaochong on 2023/7/17 11:37
 */
public class ParentChildThreadLocalDemo {
	private static ThreadLocal<User> EXTEND_THREAD_LOCAL = new InheritableThreadLocal<>();

	public static void main(String[] args) throws InterruptedException {
		User u = new User();
		u.name = "zc";
		u.age = 30;
		EXTEND_THREAD_LOCAL.set(u);

		// 创建子线程
		Thread childThread = new Thread(() -> {
			while (true) {
				// 子线程获取父线程中的ThreadLocal变量的值
				User value = EXTEND_THREAD_LOCAL.get();
				System.out.println("子线程获取到的值：" + value.name + "~" + value.age);
				value.name = "child-zc";
				value.age = 40;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					throw new RuntimeException(e);
				}
			}
		});

		// 启动子线程
		childThread.start();
		Thread.sleep(3000);
		System.out.println(Thread.currentThread().getName() + EXTEND_THREAD_LOCAL.get().name + "~" + EXTEND_THREAD_LOCAL.get().age);
	}
}

class User {
	public String name;
	public int age;
}
