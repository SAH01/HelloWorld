package thread_communicate;

/**
 * @author Administrator
 * @date 2022-09-25 21:39
 */
public class TestVolatile {
	private static volatile boolean flag=true;
	public static void main(String[] args){
		new Thread(new Runnable() {
			public void run() {
				while (true){
					if(flag){
						System.out.println("线程A");
						flag=false;
					}
				}
			}
		}).start();

		new Thread(new Runnable() {
			public void run() {
				while (true){
					if(!flag){
						System.out.println("线程B");
						flag=true;
					}
				}
			}
		}).start();
	}
}