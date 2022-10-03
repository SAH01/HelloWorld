package algorithm.stack;

import java.lang.Iterable;
import java.util.Iterator;

/**
 * 链表栈
 * @author Administrator
 * @date 2022-09-12 17:26
 */
public class StackLinked<E> implements Iterable<E>{

	@Override
	/**
	 *iterator 方法是实现Iterable接口必须实现的方法
	 * 可以直接返回自己定义的迭代器类
	 */
	public Iterator<E> iterator() {
		return new MyIter();
	}

	/**
	 * 实现Iterator接口
	 * 实现hasNext、next
	 */
	private class MyIter implements Iterator<E>{
		@Override
		public boolean hasNext() {
			return header!=null;
		}

		@Override
		public E next() {
			return pop();
		}
	}

	/**
	 * 定义一个Node结点类
	 * 用于存储数据以及下一个结点
	 * @param <E>
	 */
	public static class Node<E>{
		E element;      // 数据
		Node<E> next;   // 下一个结点
		public Node(E element, Node<E> next) {
			this.element = element;
			this.next = next;
		}
	}

	private Node<E> header;     // 头结点 栈顶
//	private int elementLength;  // 链表和栈的长度

	public boolean push(E e){
		// header永远指向的是新结点　而新结点在初始化的时候会把next赋值为上一个header
		header = new Node<>(e,header);
//		elementLength++;
		return true;
	}

	// 出栈
	public E pop (){
		if (header == null)
		{
			System.out.println("栈是空的！");
		}
		// 获取当前数值
		E item = header.element;
		// 改变header的指向，指向下一个
		header = header.next;
		// 链表长度减一
//		elementLength--;

		return item;
	}

	/**
	 * 只返回栈顶元素，不改变栈的长度。
	 * @return
	 */
	public E peek(){
		return (E) header.element;
	}

	public static void main(String[] args) {
		StackLinked myLinkedStack = new StackLinked();
		myLinkedStack.push("1");
		myLinkedStack.push("2");
		myLinkedStack.push("3");
		Iterator iterator = myLinkedStack.iterator();
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}