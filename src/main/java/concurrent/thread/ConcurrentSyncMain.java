package concurrent.thread;

import concurrent.ShowMeYourProcessor;
import concurrent.thread.sync.ConditionSync;
import concurrent.thread.sync.ObjWaitSync;
import concurrent.thread.sync.SleepSync;

/**
 * @author zhaochong on 2023/5/7 09:25
 */
public class ConcurrentSyncMain {

	public static void main(String[] args) throws InterruptedException {
		ShowMeYourProcessor processors1 = new SleepSync();
		// processors1.getAll().forEach(Thread::start);

		ShowMeYourProcessor processors2 = new ObjWaitSync();
		// processors2.getAll().forEach(Thread::start);

		ShowMeYourProcessor processors3 = new ConditionSync();
		processors3.getAll().forEach(Thread::start);
	}
}
