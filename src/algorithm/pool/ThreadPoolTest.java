package algorithm.pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author Administrator
 * @date 2022-09-18 15:24
 */
public class ThreadPoolTest {
	public static void main(String[] args) {
		// 创建了一个固定长度为5的线程池
		ExecutorService executor = Executors.newFixedThreadPool(5);
		for (int i = 0; i < 10; i++) {
			executor.submit(() -> {
				System.out.println("thread id is: " + Thread.currentThread().getId());
				try {
					Thread.sleep(1000L);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			});
		}
		executor.shutdown();
	}
}