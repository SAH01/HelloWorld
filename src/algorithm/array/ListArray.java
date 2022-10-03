package algorithm.array;

import java.util.Arrays;
import java.util.Iterator;

/**
 * @author Administrator
 * @date 2022-09-11 16:56
 * 实现一个数组
 */
public class ListArray<E> implements Iterable<E> {

	private Object[] elementData;   // Object存放数据

	public ListArray(int capacity)    // 构造方法 初始化容量大小
	{
		// 指定长度 初始化数组 new 出一块空间
		elementData = new Object[capacity];
	}

	/**
	 * 直接添加新元素
	 * @param element
	 * @return
	 */
	public boolean add (E element)
	{
		int size = elementData.length;  // 获取当前数组大小
		int newCapacity = size+1;   // 扩容+1
		// 此处发生性能消耗，新增数据时，需要扩容，整体数据需要复制迁移，实际上arraylist是1.5扩容！
		elementData = Arrays.copyOf(elementData,newCapacity); // 把旧的空间复制一份到新的空间并+1
		elementData[size]=element;
		return true;
	}

	/**
	 * set 方法 根据索引位置新增元素
	 * @param index
	 * @param element
	 * @return
	 */
	public E set (int index ,E element)
	{
		E oldValue = (E) elementData[index];    // 获取旧位置的元素值
		elementData[index] = element;           // 新值覆盖旧值
		return oldValue;          // 返回旧值

	}
	public E get (int index)
	{
		return (E) elementData[index]; // 返回对应索引位置的值
	}

	@Override
	public Iterator<E> iterator(){
		return new MyIterator();
	}

	class MyIterator implements Iterator<E>{
		int index = 0;
		@Override
		public boolean hasNext() {
			return index != elementData.length;
		}

		@Override
		public E next() {
			return (E) elementData[index++];    // 返回下一个元素值并+1
		}

		@Override
		public void remove() {
			//
		}
	}

	public static void main(String[] args) {
		ListArray<String> myArray= new ListArray<String>(10);   // 初始化一个容量为10的数组
		myArray.set(0,"q");
		myArray.set(2,"w");
		myArray.add("新增");
		Iterator<String> iterator = myArray.iterator();     // 使用迭代器
		while (iterator.hasNext()){
			System.out.println(iterator.next());
		}
	}
}