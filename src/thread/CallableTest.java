package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author Administrator
 * @date 2022-09-14 10:28
 */
public class CallableTest {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		MyThread myThread = new MyThread();
		FutureTask futureTask = new FutureTask(myThread);

		new Thread(futureTask).start();

		Object sum = futureTask.get();

		System.out.println(sum.toString());
	}
}
class MyThread implements Callable{
	@Override
	public Object call() throws Exception {
		int sum = 0;
		for (int i = 1; i <= 100; i++) {
			sum += i;
		}
		return sum;
	}
}