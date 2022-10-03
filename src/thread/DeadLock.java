package thread;

/**
 * @author Administrator
 * @date 2022-09-14 12:32
 * 一个宝藏需要两把钥匙来打开,同时间正好来了两个人,他们一人一把钥匙,但是
 * 双方都再等着对方能交出钥匙来打开宝藏,谁都没释放自己的那把钥匙,
 * 就这样这俩人一直僵持下去,直到开发人员发现这个局面。
 */
public class DeadLock {
	public static void main(String[] args) {
		StringBuffer s1 = new StringBuffer();
		StringBuffer s2 = new StringBuffer();

		new Thread() {
			public void run() {
				synchronized (s1) {
					s1.append("a");
					s2.append("1");

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					synchronized (s2) {
						s1.append("b");
						s2.append("2");

						System.out.println(s1);
						System.out.println(s2);
					}
				}
			}
		}.start();

		new Thread(new Runnable() {
			public void run() {
				synchronized (s2) {
					s1.append("c");
					s2.append("3");

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					synchronized (s1) {
						s1.append("d");
						s2.append("4");

						System.out.println(s1);
						System.out.println(s2);
					}
				}
			}
		}).start();
	}
}