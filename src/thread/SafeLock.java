package thread;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock 是显式锁 代码块锁 手动上锁和开锁
 * synchronized是隐式锁 代码块锁和方法锁
 * 使用Lcok锁，JVM将花费较少的时间来调度线程，性能更好。并且具有更好的拓展性（提供更多的子类）
 * @author Administrator
 * @date 2022-09-14 11:23
 */
public class SafeLock {
	public static void main(String[] args) {
		SafeLockThread safeLockThread = new SafeLockThread();
		Thread t1 = new Thread(safeLockThread);
		Thread t2 = new Thread(safeLockThread);
		Thread t3 = new Thread(safeLockThread);

		t1.start();
		t2.start();
		t3.start();
	}
}

class SafeLockThread implements Runnable{
	private int tickets = 100;
	private ReentrantLock lock = new ReentrantLock();

	@Override
	public void run() {
		while (tickets>0) {
			try {
				//在这里锁住，有点类似同步监视器
				lock.lock();
				if (tickets > 0) {
					Thread.sleep(100);
					System.out.println(Thread.currentThread().getName() + ":\t票号:" +
							tickets + "\t剩余票数:" + --tickets);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				//操作完成共享数据后在这里解锁
				lock.unlock();
			}
		}
	}
}