package algorithm.pool;

/**
 * @author Administrator
 * @date 2022-09-18 17:43
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * 线程池工具类
 * @author Litluecat
 */
public class ThreadPoolUtils {

	/**
	 * 允许同时执行的线程数
	 */
	private static Integer corePoolSize = 3;
	/**
	 * 允许创建的最大线程数
	 */
	private static Integer maximumPoolSize = 50;
	/**
	 * 表示线程没有任务执行时最多保持多久时间会终止
	 */
	private static Long keepAliveTime = 5000L;
	/**
	 * 参数keepAliveTime的时间单位
	 * TimeUnit.DAYS;               //天
	 * TimeUnit.HOURS;             //小时
	 * TimeUnit.MINUTES;           //分钟
	 * TimeUnit.SECONDS;           //秒
	 * TimeUnit.MILLISECONDS;      //毫秒
	 * TimeUnit.MICROSECONDS;      //微妙
	 * TimeUnit.NANOSECONDS;       //纳秒
	 */
	private static TimeUnit unit = TimeUnit.MILLISECONDS;
	/**
	 * 一个阻塞队列策略，用来存储等待执行的任务
	 * ArrayBlockingQueue
	 * LinkedBlockingQueue 无界队列，就是没有指定容量，可以无限大
	 * SynchronousQueue
	 * PriorityBlockingQueue
	 */
	private static BlockingQueue workQueue = new LinkedBlockingQueue<>(maximumPoolSize);
	/**
	 *用于设置创建线程的工厂,比如设置daemon和优先级等
	 */
	private static ThreadFactory threadFactory = new ThreadFactory() {
		@Override
		public Thread newThread(Runnable r) {
			Thread thread = new Thread(r);
			return thread;
		}
	};
	/**
	 * 表示当拒绝处理任务时的策略
	 * 1、AbortPolicy：直接抛出异常。
	 * 2、CallerRunsPolicy：只用调用者所在线程来运行任务。
	 * 3、DiscardOldestPolicy：丢弃队列里最近的一个任务，并执行当前任务。
	 * 4、DiscardPolicy：不处理，丢弃掉。
	 * 5、也可以根据应用场景需要来实现RejectedExecutionHandler接口自定义策略。如记录日志或持久化不能处理的任务。
	 */
	private static RejectedExecutionHandler  handler = new ThreadPoolExecutor.CallerRunsPolicy();
	private static ThreadPoolExecutor threadPoolExecutor = null;

	/**
	 * 获取线程池对象
	 * @return
	 */
	public static ThreadPoolExecutor getThreadPoolExecutor() {
		if (null == threadPoolExecutor) {
			ThreadPoolExecutor t;
			synchronized (ThreadPoolExecutor.class) {
				t = threadPoolExecutor;
				if (null == t) {
					synchronized (ThreadPoolExecutor.class) {
						t = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
					}
					threadPoolExecutor = t;
				}
			}
		}
		return threadPoolExecutor;
	}

	/**
	 * 设置允许同时执行的线程数
	 * @param corePoolSize
	 */
	public static void setCorePoolSize(int corePoolSize){
		ThreadPoolUtils.corePoolSize = corePoolSize;
	}
	/**
	 * 设置阻塞策略
	 * @param handler
	 */
	public static void setHandler(RejectedExecutionHandler  handler){
		ThreadPoolUtils.handler = handler;
	}
	/**
	 * 设置线程工厂对象
	 * @param threadFactory
	 */
	public static void setThreadFactory(ThreadFactory threadFactory){
		if(null != threadFactory){
			ThreadPoolUtils.threadFactory = threadFactory;
		}else{
			System.out.println("请确认ThreadFactory线程工厂对象不为空");
		}
	}


	public static void main(String[] args){
		int count = 8;
		System.out.println("现有火车票"+count+"张，开始售票：");
		String[] user = {"小华","小明","大名","花花","小花","螺蛳粉","火锅","小酥肉","五花肉","雪花牛肉"};
		try{
			RunnableTest test = new RunnableTest(count,60);
			MyThreadFactory myThreadFactory = new MyThreadFactory();
			ThreadPoolUtils.setThreadFactory(myThreadFactory.get());
			ThreadPoolUtils.setHandler(new RejectedExecutionHandler() {
				@Override
				public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
					System.out.println(r.toString()+"执行了拒绝策略");
				}
			});
			ThreadPoolUtils.setCorePoolSize(user.length);
			ThreadPoolExecutor threadPool = ThreadPoolUtils.getThreadPoolExecutor();

			for(String userName : user){
				myThreadFactory.setUserName(userName);
				threadPool.execute(test);
			}

			Thread.sleep(1000L);
			System.out.println("======================================================================================");
			System.out.println("获取活动的线程数："+threadPool.getActiveCount());
			System.out.println("线程池需要执行的任务数量："+threadPool.getTaskCount());
			System.out.println("线程池在运行过程中已完成的任务数量："+threadPool.getCompletedTaskCount());
			threadPool.shutdown();
			System.out.println("======================================================================================");

			String successUserName = "恭喜以下用户抢到车票：";
			for(String userName : test.getSuccessUserList()){
				successUserName += userName+" ";
			}
			System.out.println(successUserName);

		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}

class MyThreadFactory {

	private String userName;

	public ThreadFactory get(){
		ThreadFactory threadFactory = new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				Thread thread = new Thread(r,userName);
				return thread;
			}
		};
		return threadFactory;
	}

	public void setUserName(String userName){
		this.userName = userName;
	}
}
class RunnableTest implements Runnable {
	//随机数
	Random r = new Random();
	//抢票成功概率
	int success;
	//总票数
	int count;
	List<String> successUserList = new ArrayList<>();

	public RunnableTest(int count, int success) {
		this.count = count;
		this.success = success;
	}

	public List<String> getSuccessUserList() {
		return successUserList;
	}

	@Override
	public void run() {
		try {
			String userName = Thread.currentThread().getName();
			boolean start = successUserList.contains(userName);

			while (!start) {
				if (0 < count) {
					synchronized (this) {
						if (success < r.nextInt(100) && !start) {
							count--;
							System.out.println(userName + "：已抢到：剩余车票：" + (count) + "张");
							successUserList.add(userName);
							start = true;
						} else {
							System.out.println(userName + "：正在抢票中，请稍后。。。");
						}
					}
				} else {
					System.out.println(userName + "：车票已售毕：剩余车票：" + (count) + "张");
					start = true;
				}
			}
			Thread.sleep(500L);
		} catch (InterruptedException e) {
			System.out.println("线程休眠异常。。。");
		}
	}
}