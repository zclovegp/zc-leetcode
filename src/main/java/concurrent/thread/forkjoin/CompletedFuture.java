package concurrent.thread.forkjoin;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author zhaochong on 2024/3/14 19:05
 */
public class CompletedFuture {

    public static void main(String[] args) {

        ThreadPoolExecutor pool1 = new ThreadPoolExecutor(5, 5, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new CallerRunsPolicy());
        ThreadPoolExecutor pool2 = new ThreadPoolExecutor(5, 5, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), new CallerRunsPolicy());

        List<CompletableFuture<String>> returnList = new ArrayList<>();
        List<CompletableFuture<Void>> voidList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> String.valueOf(finalI), pool1);
            CompletableFuture<Void> futureVoid = CompletableFuture.runAsync(() -> System.out.println(finalI), pool2);
            returnList.add(future);
            voidList.add(futureVoid);
        }
        List<String> res = returnList.stream().map(CompletableFuture::join).collect(Collectors.toList());
        List<Void> resVoid = voidList.stream().map(CompletableFuture::join).collect(Collectors.toList());
    }
}
