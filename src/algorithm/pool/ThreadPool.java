package algorithm.pool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ThreadPoolExecutor.AbortPolicy;

/**
 * 线程池测试
 * @author uno
 *	5:coreThreadCount:核心线程数，即前5个线程来了就创建线程，加入线程池
 *	10：maxthreadCount：最大线程数，当core满时，会向workBlockQueue加入线程，等待执行
 *	new ArrayBlockingQueue(5)：当blockqueue中满，会根据maxThreadCount再创建加入线程池
 *	当maxThreadCount满时，RejectedExecutionHandler拒绝策略上场
 *	shutdown:关闭线程池，等待缓存队列中线程执行完成
 *	shutdownNow：立即关闭线程池
 */

/**
 *  引用网上一段话
 *  1.当线程池小于corePoolSize时，新提交任务将创建一个新线程执行任务，即使此时线程池中存在空闲线程。
 2.当线程池达到corePoolSize时，新提交任务将被放入workQueue中，等待线程池中任务调度执行
 3.当workQueue已满，且maximumPoolSize>corePoolSize时，新提交任务会创建新线程执行任务
 4.当提交任务数超过maximumPoolSize时，新提交任务由RejectedExecutionHandler处理
 5.当线程池中超过corePoolSize线程，空闲时间达到keepAliveTime时，关闭空闲线程
 6.当设置allowCoreThreadTimeOut(true)时，线程池中corePoolSize线程空闲时间达到keepAliveTime也将关闭
 * @author uno
 *
 */
public class ThreadPool {

	private static ThreadPoolExecutor executor = null;
	static {
		executor = new ThreadPoolExecutor(5, 10, 200,
				TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(5), new MyTaskThreadFactory(), new AbortPolicy()
		);
		executor.prestartCoreThread(); //预启动一个线程
//		executor.prestartAllCoreThreads();//预启动corePoolSize个线程到线程池
	}


	public static void main(String[] args) {
		System.out.println("线程池线程数： "+executor.getPoolSize());
		for(int i = 0; i<15;i++){
			System.out.println(executor.isShutdown());
			executor.execute(new MyTask(i, "uno线程："+i));
			System.out.println("线程池线程数： "+executor.getPoolSize());
			System.out.println("阻塞队列中线程数：" + executor.getQueue().size());
			System.out.println("已执行完线程数：" + executor.getCompletedTaskCount());
		}
		executor.shutdown(); //关闭线程池
	}


}
/**
 * 自定义线程工厂
 * @author uno
 *
 */
class MyTaskThreadFactory implements ThreadFactory{
	private static String FACTORYNAME = "MYTASKFACTORY";
	private static List<? super Thread> threadList = new ArrayList<>();

	public void setFactoryName(String name){
		FACTORYNAME = name;
	}
	@Override
	public Thread newThread(Runnable r) {
		synchronized (r) {
			//这里可以自定义个Thread，用了处理在创建线程前后预处理
			Thread t = new Thread(r, FACTORYNAME + threadList.size());
			threadList.add(t);
			return t;
		}
	}
}
/**
 * 任务线程类
 * @author uno
 * 区别于线程工厂创建的线程：线程工厂创建的线程是用来执行任务线程（MyTask）的线程
 */
class MyTask extends Thread {
	private int num;
	public MyTask(int i, String name) {
		super(name);
		this.num = i;
	}
	@Override
	public void run() {
		try {
			System.out.println(this.getName() + ", 正执行task " + num);
			Thread.sleep(2000);
		} catch (Exception e) {
		}
		System.out.println(this.getName() + ", task " + num + " 执行完成");
	}
}

