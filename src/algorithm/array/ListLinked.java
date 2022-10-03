package algorithm.array;

import java.util.Iterator;

/**
 * 双向循环链表
 * @author Administrator
 * @date 2022-09-11 22:57
 */
public class ListLinked<E> implements Iterable<E>{
	private Node<E> last;   // 尾指针
	private Node<E> first;  // 头指针
	private int size;       // 链表长度 每次插入要 +1


	@Override
	public Iterator<E> iterator() {
		return new MyIter() ;   // 返回一个迭代器
	}

	/**
	 * 实现Iterator接口的迭代器类
	 */
	class MyIter implements Iterator<E>{
		int index = 0;      // 从0开始遍历
		@Override
		public boolean hasNext() {
			return index != size;  //如果为真 就是还有下一个
		}

		@Override
		public E next() {
			return get(index++);    //　通过get方法得到链表的item值
		}

		@Override
		public void remove() {

		}
	}

	/**
	 * 节点类
	 * @param <E>
	 */

	private static class Node<E>{
		E item;         // 元素值
		Node<E> prev;   // 前一个Node
		Node<E> next;   // 后一个Node
		public Node(Node<E> prev,E item,Node<E> next){
			this.item=item;
			this.prev=prev;
			this.next=next;
		}
	}

	public boolean addLast (E element)
	{
		// 声明一个不变的尾结点
		final Node<E> l = last;
		// 把item装入一个Node里 下面开始插入
		Node<E> newNode = new Node<E>(l,element,null);
		// 先把尾结点指向新插入的结点
		last = newNode;   // 新插进来的就是最后一个
		// 如果是这是第一个元素 那么头尾结点其实都是这个新结点
		if (l == null)
		{
			first = newNode;     // 把这个新结点赋值给头结点
		}else{
			l.next = newNode;   // 如果不是新的 那么就正常指向下一个
		}
		size++;            // 链表长度 +1
		return true;
	}

	public E set (int index , E element)
	{
		// 先判断index在哪
		Node<E> x = findNode(index);
		E oldValue = x.item;
		x.item = element;
		return oldValue;        // 返回修改前的item值
	}

	/**
	 * 找到指定索引上的Node并返回
	 * @param index
	 * @return
	 */
	private Node<E> findNode(int index){
		if(index < (size >> 1) )    // 如果index索引 小于链表总长的一半 那就从前往后找 直到index位置
		{
			Node<E> x = first;
			for(int i = 0 ; i < index; i++){
				x = first.next;
			}
			return x;
		}
		// 如果index索引 大于链表总长的一半 那就从后往前找 直到index位置
		Node<E> x = last;
		for(int i = size-1 ;i > index; i--){
			x = last.prev;
		}
		return x;
	}

	/**
	 * 获得值
	 * @param index
	 * @return
	 */
	public E get(int index)
	{
		return findNode(index).item;
	}

	public static void main(String[] args) {
		ListLinked<String> myLinkedList = new ListLinked<String>();
		myLinkedList.addLast("aaa1");
		myLinkedList.addLast("aaa2");
		myLinkedList.addLast("aaa3");
		myLinkedList.addLast("aaa4");
		myLinkedList.addLast("aaa5");
		myLinkedList.set(0,"set");

		myLinkedList.forEach (s -> {
			System.out.println(s);
		});

		Iterator myIter = myLinkedList.iterator();
		while (myIter.hasNext()){
			System.out.println(myIter.next());
		}
	}
}