package thread;

/**
 * @author Administrator
 * @date 2022-09-14 9:51
 */
public class TwoThread  {
	public static void main(String[] args) {
		Thread01 thread01 = new Thread01();
		Thread02 thread02 = new Thread02();
		Thread t_1 = new Thread(thread01,"1");
		Thread t_2 = new Thread(thread01,"2");
		t_1.start();
		t_2.start();
	}
}

class Thread01 implements Runnable{
	@Override
	public void run() {
		for (int i=0; i<100;i++)
		{
			System.out.println("这是线程" + Thread.currentThread().getName());
		}
	}
}
class Thread02 implements Runnable{

	public Thread02() {
		super();
	}

	@Override
	public void run() {
		for (int i=0; i<100;i++)
		{
			System.out.println("这是线程" + Thread.currentThread().getName());
		}
	}

}