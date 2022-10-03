package algorithm.queue;

import java.util.Arrays;

/**
 * @author Administrator
 * @date 2022-09-13 17:10
 * 队列 0 1 2 3 4 5
 * head = 0 tail++
 * 左出右进 尾进头出
 * 入队的时候tail++ 出队的时候head++
 */
public class QueueArray<E>{
	private Object[] elementData;   // 数据
	private int head;               // 头索引
	private int tail;               // 尾索引
	// 构造 确定队列大小
	public QueueArray(int size) {
		this.elementData = new Object[size];
	}
	public boolean enQueue(E element){
		// 扩容问题 当尾索引大于等于队列长度的时候 需要做扩容
		if(tail >= elementData.length){
			// Arrays.copyOf
			elementData = Arrays.copyOf(elementData,elementData.length << 1);
			// 当头部位置不在0的时候 可以移动整个队列 让头索引移到队列的最前面
			if (head != 0){
				if (tail - head >= 0){
					System.arraycopy(elementData,head,elementData,0,tail-head);
				}
				tail = tail - head;     // 重新把尾索引往前移动 head 个长度
				head = 0;               // 把head置为0
			}
		}
		// 尾指针+1 入队
		elementData[tail++] = element;  // 赋值并尾索引+1
		return true;
	}

	public E deQueue (){
		if(tail == head)       // 头尾相等 队列为空
		{
			throw new RuntimeException("队列为空!");
		}
		return (E) elementData[head++]; // 出队 位置索引从 0 开始加
	}

	public static void main(String[] args) {
		QueueArray myQueue = new QueueArray(3);
		myQueue.enQueue("1");
		myQueue.enQueue("2");
		myQueue.enQueue("3");
		myQueue.enQueue("4");
		System.out.println(myQueue.deQueue());
		System.out.println(myQueue.deQueue());
		System.out.println(myQueue.deQueue());
		System.out.println(myQueue.deQueue());

		myQueue.enQueue("5");
		System.out.println(myQueue.deQueue());
	}
}