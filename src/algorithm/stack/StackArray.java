package algorithm.stack;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Administrator
 * @date 2022-09-12 16:38
 * 数组栈
 */
public class StackArray<E> implements Iterable<E> {
	// 定义一个数组
	private Object[] elementData;
	// 顶部的索引
	private int topIndex;
	// 构造方法确定栈的长度
	public StackArray(int size) {
		this.elementData = new Object[size];
	}

	/**
	 * 定义迭代器
	 * @return
	 */
	@Override
	public Iterator<E> iterator() {
		return new MyArrayStackIter();
	}
	class MyArrayStackIter implements Iterator<E>{
		@Override
		public boolean hasNext() {
			return topIndex != elementData.length;
		}
		@Override
		public E next() {
			return pop();
		}
		@Override
		public void remove() {
			pop();
		}
	}
	public boolean push(E element){
		// 判断扩容两倍
		if(topIndex >= elementData.length){
			elementData = Arrays.copyOf(elementData,elementData.length << 1);
		}
		elementData[topIndex++] = element;
		return true;
	}
	// 删除并出栈 是否删除就看topIndex本身的大小是否改变
	public E pop(){
		if(topIndex <= 0){
			throw new RuntimeException("栈为空");
		}
		return (E) elementData[--topIndex];
	}
	// 不删除出栈 栈顶元素
	public E peek(){
		if(topIndex <= 0){
			throw new RuntimeException("栈为空");
		}
		return (E) elementData[topIndex - 1];
	}
	public static void main(String[] args) {
		StackArray myArrayStack = new StackArray(5);
		myArrayStack.push("11");
		myArrayStack.push("22");
		myArrayStack.push("33");
		System.out.println("-----------");
		System.out.println(myArrayStack.pop());
		System.out.println(myArrayStack.pop());
		System.out.println(myArrayStack.pop());
		System.out.println(myArrayStack.pop());
//		Iterator iterator = myArrayStack.iterator();
//		while (iterator.hasNext()){
//			System.out.println(iterator.next());
//		}
	}
}