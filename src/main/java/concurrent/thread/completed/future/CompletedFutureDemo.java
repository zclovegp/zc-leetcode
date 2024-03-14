package concurrent.thread.completed.future;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * test
 * @author zhaochong on 2023/8/10 14:17
 */
public class CompletedFutureDemo {

    public static ThreadPoolExecutor POOL = new ThreadPoolExecutor(10, 300, 100L, TimeUnit.SECONDS, new LinkedBlockingQueue<>());

    public static void main(String[] args) {
        voidCompletableFutureTest();
        returnCompletableFutureTest();
        POOL.shutdown();
    }

    private static void voidCompletableFutureTest() {
        List<CompletableFuture<Void>> result = Lists.newArrayList();

        for (int i = 0; i < 100; i++) {
            CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "here is not return =======> first");
                // 这里的异常计算抛出去，框架层还是可以捕获到的，不会造成线程的假死
                // 但是如果是一个定时任务的线程池，需要注意线程处理方法需要try住避免造成死锁
                throw new RuntimeException("失败了别慌哦!");
            }, POOL);
            result.add(completableFuture);
        }
        System.out.println("开始执行 first!");
        try {
            CompletableFuture.allOf(result.toArray(new CompletableFuture[0])).join();
        } catch (Exception e) {
            System.out.println("join过程中出现异常");
        }
        System.out.println("first all is done!");
    }

    private static void returnCompletableFutureTest() {
        List<CompletableFuture<String>> result = Lists.newArrayList();

        for (int i = 0; i < 20; i++) {
            int finalI = i;
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> String.valueOf(finalI), POOL);
            result.add(completableFuture);
        }

        List<String> xx = result.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
        System.out.println("second all is done" + xx);
    }
}
