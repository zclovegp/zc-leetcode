package concurrent.thread.submit;

import com.google.common.collect.Lists;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaochong on 2024/3/14 19:20
 */
public class ThreadPoolSubmit {

    public static void main(String[] args) {
        ThreadPoolExecutor pool = new ThreadPoolExecutor(8, 8, 60L, TimeUnit.SECONDS, new SynchronousQueue<>(), new CallerRunsPolicy());

        List<Callable<String>> callables = getCallables();

        int i = 0;
        for (Callable<String> callable : callables) {
            System.out.println("已经将任务提交到线程池中" + i);
            pool.submit(callable);
            i++;
        }
    }

    private static List<Callable<String>> getCallables() {
        List<Callable<String>> callables = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            Callable<String> callable = new Callable<String>() {
                @Override
                public String call() throws Exception {
                    Thread.sleep(1000);
                    System.out.println(String.valueOf(finalI));
                    return String.valueOf(finalI);
                }
            };
            callables.add(callable);
        }
        return callables;
    }
}
