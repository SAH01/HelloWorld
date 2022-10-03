package thread;

/**
 * 同步方法
 * extends Thread同步监视器是：当前类本身。
 * @author Administrator
 * @date 2022-09-14 11:21
 */
public class SyncExtends {
	public static void main(String[] args) {
		SyncExtendsThread t1 = new SyncExtendsThread();
		SyncExtendsThread t2 = new SyncExtendsThread();
		SyncExtendsThread t3 = new SyncExtendsThread();
		t1.setName("窗口1");
		t2.setName("窗口2");
		t3.setName("窗口3");
		t1.setPriority(Thread.MIN_PRIORITY);    // 设置优先级 1 - 10 NORM = 5
		t3.setPriority(Thread.MAX_PRIORITY);
		t1.start();
		t2.start();
		t3.start();
	}
}

class SyncExtendsThread extends Thread {
	public static int tiketsNum = 100;

	@Override
	public void run() {
		while (tiketsNum > 0) {
			show();
		}
	}

	public static synchronized void show() {
		// 同步监视器：Winddoe03Thread.class
		// 不加static话同步监视器为t1 t2 t3所以错误
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