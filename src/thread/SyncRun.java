package thread;

/**
 * 同步方法  implements Runnable
 * 将所要同步的代码放到一个方法中，将方法声明为synchronized同步方法。之后可以在run()方法中调用同步方法。
 * @author Administrator
 * @date 2022-09-14 11:19
 * 同步方法仍然涉及到同步监视器，只是不需要我们显示的声明。
 * 非静态的同步方法，同步监视器是：this。
 * 静态的同步方法，同步监视器是：当前类本身。
 */
public class SyncRun {
	public static void main(String[] args) {
		SyncRunThread ticketsThread02 = new SyncRunThread();
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

class SyncRunThread implements Runnable {
	private int tiketsNum = 100;

	@Override
	public void run() {
		while (tiketsNum > 0) {
			show();
		}
	}

	private synchronized void show() { //同步监视器：this
		if (tiketsNum > 0) {
			try {
				//手动让线程进入阻塞,增大安全性发生的概率
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(Thread.currentThread().getName() + ":\t票号:" +
					tiketsNum + "\t剩余票数:" + --tiketsNum);
		}
	}
}