package thread;

/**
 * @author Administrator
 * @date 2022-09-14 9:44
 */
public class OneThread extends Thread {
	// 给线程命名，弄一个有参的构造器, 调用父类的构造器，将name传过去
	public OneThread(String name) {
		super(name);
	}
	@Override
	public void run() {
		//输出100以内的偶数
		for (int i = 0; i < 100; i++) {
			if (i % 2 == 0){
				System.out.println(Thread.currentThread().getName()+":\t"+i);
			}
		}
	}

	public static void main(String[] args) {
		OneThread t1 = new OneThread("t1");
		OneThread t2 = new OneThread("t2");
		t1.start();
		t2.start();

	}
}