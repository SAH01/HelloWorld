package algorithm.queue;

/**
 * @author Administrator
 * @date 2022-09-13 17:50
 */
public class QueueLinked<E>{

	private static class Node<E>{
		E item;
		Node<E> next;

		public Node(E item, Node<E> next) {
			this.item = item;
			this.next = next;
		}
	}

	private Node<E> head;   // 头指针
	private Node<E> tail;   // 尾指针

	public boolean enQueue (E e)
	{
		Node<E> newNode = new Node<>(e,null);
		// 尾部入队
		if (tail == null){
			// 初始化 尾指向新加入的元素
			tail = newNode;
			// 头指向尾
			head = tail;
			return true;
		}
		else{
			// 如果初始化已经完成了 那么之后的元素就从尾部开始往后指
			tail.next = newNode;
			tail = newNode;     // 尾指针的next要指向新结点 同时尾指针本身也要指向新结点
		}
		return true;
	}
	public E deQueue (){
		if (head == null)
		{
			throw new RuntimeException("队列为空！");
		}
		E item  = head.item;
		head = head.next;
		return item;
	}

	public static void main(String[] args) {
		QueueLinked myLinkedQueue = new QueueLinked();
		myLinkedQueue.enQueue("11");
		myLinkedQueue.enQueue("22");

		System.out.println(myLinkedQueue.deQueue());
		System.out.println(myLinkedQueue.deQueue());
		System.out.println(myLinkedQueue.deQueue());
	}
}