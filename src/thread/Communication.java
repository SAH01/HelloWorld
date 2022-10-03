package thread;

/**
 * @author Administrator
 * @date 2022-09-18 18:00
 */

public class Communication {
	public static void main(String[] args) {
		CommunicationThread communicationThread = new CommunicationThread();
		Thread t1 = new Thread(communicationThread);
		Thread t2 = new Thread(communicationThread);
		Thread t3 = new Thread(communicationThread);

		t1.start();
		t2.start();
		t3.start();
	}
}

class CommunicationThread implements Runnable {
	int Num = 1;

	@Override
	public void run() {
		while (true) {
			synchronized (this) {
				notifyAll();
				if (Num <= 100) {
					System.out.println(Thread.currentThread().getName() + ":\t" + Num);
					Num++;
					try {
						wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}else{
					break;
				}
			}

		}
	}
}
