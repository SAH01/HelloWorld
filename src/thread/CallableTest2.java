package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @author Administrator
 * @date 2022-09-14 10:32
 */
public class CallableTest2 {
	public static void main(String[] args) throws Exception {

		FutureTask futureTask1 = new FutureTask(new Callable() {
			@Override
			public String call() throws Exception {
				Thread.sleep(3000);
				System.out.println(Thread.currentThread().getName()+"---------哈哈哈  1------");
				return Thread.currentThread().getName()+"---------hello  1------";
			}
		});

		FutureTask futureTask2 = new FutureTask(new Callable() {
			@Override
			public String call() throws Exception {
				System.out.println(Thread.currentThread().getName()+"---------哈哈哈  2------");

				return Thread.currentThread().getName()+"---------hello  2------";
			}
		});

		new Thread(futureTask1).start();
		new Thread(futureTask2).start();
		System.out.println(futureTask1.get());
		System.out.println(futureTask2.get());
	}
}