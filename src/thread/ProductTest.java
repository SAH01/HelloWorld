package thread;

/**
 * @author Administrator
 * @date 2022-09-14 12:40
 */

/**
 * - 经典例题：生产者和消费着问题
 * 生产者( Productor)将产品交给店员( Clerk),而消费者( (Customer)从店员处取走产品,
 * 店员一次只能持有固定数量的产品(比如:20),如果生产者试图生产更多的产品,店员会叫生产者停一下,
 * 如果店中有空位放产品了再通知生产者继续生产; 如果店中没有产品了,店员会告诉消费者等一下,
 * 如果店中有产品了再通知消费者来取走产品。
 *
 * 分析：
 * 1.是多线程问题，可以假设多个消费这和多个生产者是多线程的
 * 2.存在操作的共享数据，生产和购买时都需要操作经销商的库存存量。
 * 3.处理线程安全问题。
 * 4.三个类：生产者，经销商，消费者。经销商被生产者和消费者共享。生产者读取经销商库存，当库存不够时，生产产品
 * 并发给经销商，操作经销商库存+1。消费者读取经销商库存，当有库存时，方可进行购买，购买完成后，经销商库存-1.
 */
public class ProductTest {
	public static void main(String[] args) {
		Clerk clerk = new Clerk();
		Producer p1 = new Producer(clerk);
		Producer p2 = new Producer(clerk);
		p1.setName("生产者1");
		p2.setName("生产者2");

		Consumer c1 = new Consumer(clerk);
		Consumer c2 = new Consumer(clerk);
		c1.setName("消费者1");
		c2.setName("消费者2");

		p1.start();
		c1.start();
	}
}

class Clerk {
	private int productNum;

	public Clerk() {
		this.productNum = 0;
	}

	public int getProductNum() {
		return productNum;
	}

	public void setProductNum(int productNum) {
		this.productNum = productNum;
	}
}

class Producer extends Thread {
	private Clerk clerk;

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "开始生产......");

		while(true){
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			produce();
		}
	}

	public Producer(Clerk clerk) {
		if (clerk != null) {
			this.clerk = clerk;
		}
	}

	private void produce() {
		synchronized (ProductTest.class) {
			ProductTest.class.notify();
			if (clerk.getProductNum() < 20) {
				clerk.setProductNum(clerk.getProductNum() + 1);
				System.out.println(Thread.currentThread().getName() + ":\t生产完成第 " + clerk.getProductNum() + " 个产品");
			}else {
				try {
					ProductTest.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

}

class Consumer extends Thread {
	private Clerk clerk;

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName() + "开始消费......");

		while(true){
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			buy();
		}
	}

	public Consumer(Clerk clerk) {
		if (clerk != null) {
			this.clerk = clerk;
		}
	}

	private void buy(){
		synchronized (ProductTest.class) {
			ProductTest.class.notify();
			if (clerk.getProductNum() > 0) {
				System.out.println(Thread.currentThread().getName() + ":\t购买完成第 " + clerk.getProductNum() + " 个产品");
				clerk.setProductNum(clerk.getProductNum() - 1);
			}else {

				try {
					ProductTest.class.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
