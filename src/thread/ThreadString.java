package thread;

/**
 * @author Administrator
 * @date 2022-09-14 8:58
 */
public class ThreadString {
	public static void main(String[] argaa) {
		MyString sb = new MyString();
		StringBuilder sbBuilder = new StringBuilder();
		StringBuffer stringBuffer = new StringBuffer();
		for (int i = 0; i < 100; i++) {
			new Thread(new Runnable() {
				@Override
				public void run() {
					for (int j = 0; j < 1000; j++) {
						sb.append(1);
						sbBuilder.append("1");
						stringBuffer.append("1");
						System.out.println(sb.getNum() + "-" + sbBuilder.length()
								+ "-" + stringBuffer.length());
						System.out.println(Thread.currentThread().getName());   // 输出线程名字
					}
				}
			},"hhh").start();
		}

	}
}

class MyString {
	private Integer num = 0;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public synchronized void append(Integer num) {
		this.num = this.num + num;
	}
}