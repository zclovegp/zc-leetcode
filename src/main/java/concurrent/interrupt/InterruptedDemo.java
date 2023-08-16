package concurrent.interrupt;
/**
 * @author zhaochong on 2023/8/15 17:44
 */
public class InterruptedDemo implements Runnable {
  public void run() {
    try {
      while (true) {
        boolean a = Thread.currentThread().isInterrupted();
        System.out.println("in run() - about to sleep for 20 seconds-------" + a);
        Thread.sleep(20000);
        System.out.println("in run() - woke up");
      }
    } catch (InterruptedException e) {
      // 如果不加上这一句，那么cd将会都是false，因为在捕捉到InterruptedException异常的时候就会自动的中断标志置为了false
      Thread.currentThread().interrupt();
      boolean c = Thread.interrupted();
      boolean d = Thread.interrupted();
      boolean f = Thread.interrupted();
      System.out.println("c=" + c);
      System.out.println("d=" + d);
      System.out.println("f=" + f);
      throw new RuntimeException(e);
    }
  }

  public static void main(String[] args) {
    InterruptedDemo si = new InterruptedDemo();
    Thread t = new Thread(si);
    t.start();
    // 主线程休眠2秒，从而确保刚才启动的线程有机会执行一段时间
    try {
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    System.out.println("in main() - interrupting other thread");
    // 中断线程t
    t.interrupt();
    System.out.println("in main() - leaving");
  }
}
