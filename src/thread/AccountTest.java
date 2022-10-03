package thread;

/**
 * @author Administrator
 * @date 2022-09-14 12:40
 */

/**
 * 练习1
 * 银行有一个账户
 * 有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 * 分析：
 * 1.是否有多个线程问题？ 是，有两个储户线程。
 * 2.是否有共享数据？ 是，两个储户向同一个账户存钱
 * 3.是否有线程安全问题： 有
 */
public class AccountTest {
	public static void main(String[] args) {
		Account acct = new Account();
		Customer c1 = new Customer(acct);
		Customer c2 = new Customer(acct);

		c1.setName("储户1");
		c2.setName("储户2");

		c1.start();
		c2.start();

	}
}

class Account {
	private double accountSum;

	public Account() {
		this.accountSum = 0;
	}

	public Account(double accountSum) {
		this.accountSum = accountSum;
	}

	//存钱
	public void deppsit(double depositNum) {
		synchronized (this) {
			if (depositNum > 0) {
				accountSum = accountSum + depositNum;
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName() + ": 存钱成功，当前余额为：\t" + accountSum);
			}
		}

	}

}

class Customer extends Thread {
	private Account acct;

	public Customer(Account acct) {
		this.acct = acct;
	}

	@Override
	public void run() {
		for (int i = 0; i < 3; i++) {
			acct.deppsit(1000);
		}
	}
}
