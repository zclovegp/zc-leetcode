package jvm;

/**
 * -XX:+PrintGC -XX:-DoEscapeAnalysis（关闭逃逸分析）
 * -XX:+PrintGC -XX:+DoEscapeAnalysis（开启逃逸分析）
 * <p>
 * -XX:+EliminateLocks可以开启同步消除
 * -XX:+EliminateAllocations可以开启标量替换
 *
 * @author zhaochong on 2023/6/9 22:57
 */
public class 逃逸分析 {

	public static void main(String[] args) {
		for (int i = 0; i < 1000000000; i++) {
			createObj();
		}
	}

	public static void createObj() {
		User user = new User();
		user.setAge("1000000000");
		user.setName("小郭的周末小郭的周末小郭的周末小郭的周末小郭的周末小郭的周末小郭的周末");
	}
}

class User {
	private String name;
	private String age;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}
}
