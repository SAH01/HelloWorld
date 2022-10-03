package thread;

/**
 * 同步代码块
 * synchronized
 * 同步监视器就是一个普通的Java对象，这个对象必须被所有线程公有
 * 根据实现线程的不同方式 判断是否需要加static
 * @author Administrator
 * @date 2022-09-14 11:17
 */

/**
 * 锁的选择：
 *
 * 自行创建，共用对象，如下面demo中的Object对象。
 *
 * 使用this表示当前类的对象
 *
 * 继承Thread的方法中的锁不能使用this代替，因为继承thread实现多线程时，会创建多个子类对象来代表多个线程，
 * 这个时候this指的时当前这个类的多个对象，不唯一，无法当作锁。
 *
 * 实现Runnable接口的方式中，this可以当作锁，因为这种方式只需要创建一个实现类的对象，
 * 将实现类的对象传递给多个Thread类对象来当作多个线程，this就是这个一个实现类的对象，
 * 是唯一的，被所有线程所共用的对象。
 *
 * 使用类当作锁，以下面demo为例，其中的锁可以写为WindowThread.class, 从这里可以得出结论，类也是一个对象
 *
 * 优点：同步的方式，解决了线程安全的问题
 *
 * 缺点：操作同步代码时，只能有一个线程参与，其他线程等待。相当于时一个单线程的过程，效率低。
 */
public class SafeTicketsWindow {
	public static void main(String[] args) {
		WindowThread ticketsThread02 = new WindowThread();
		Thread t1 = new Thread(ticketsThread02);
		Thread t2 = new Thread(ticketsThread02);
		Thread t3 = new Thread(ticketsThread02);

		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");

		t1.start();
		t2.start();
		t3.start();
	}
}

class WindowThread implements Runnable {
	private int tiketsNum = 100;

	//由于，Runnable实现多线程，所有线程共用一个实现类的对象，所以三个线程都共用实现类中的这个Object类的对象。
	Object obj = new Object();
	//如果时继承Thread类实现多线程，那么需要使用到static Object obj = new Object();

	public void run() {

		//Object obj = new Object();
		//如果Object对象在run()方法中创建，那么每个线程运行都会生成自己的Object类的对象，
		//并不是三个线程的共享对象，所以并没有给加上锁。

		while (true) {
			synchronized (obj) {
				if (tiketsNum > 0) {
					try {
						//手动让线程进入阻塞,增大安全性发生的概率
						Thread.sleep(100);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(Thread.currentThread().getName() + ":\t票号:" +
							tiketsNum + "\t剩余票数:" + --tiketsNum);
				} else {
					break;
				}
			}
		}
	}
}