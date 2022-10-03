package algorithm.heap;

/**
 * 小顶堆
 * @author Administrator
 * @date 2022-09-17 14:18
 */

public class HeapNode {

	private int[] heap; // 数据
	private int size;   // 长度

	/**
	 * 构造方法 确定堆的大小
	 * @param capacity
	 */
	public  HeapNode(int capacity){
		heap = new int[capacity];
	}

	/**
	 * 赋值 小的在根节点
	 * @param n
	 */
	public void offer(int n){
		// n 是要新增的值 用i取到堆的长度
		int i = size;
		// 在这里判断 是否需要交换位置
		while (i > 0){
			// 取到上面的值 无符号 除以2
			int parent = (i-1) >>> 1;
			int v = heap[parent];
			if (n >= v){
				break;
			}
			heap[i] = v;
			i = parent;
		}
		// 交换
		heap[i] = n;
		size++;
	}

	public int poll(){
		//将堆顶元素 弹出，并且删除
		//需要重新构建堆
		int top = heap[0];
		int[] newHeap = new int[size-1];
		System.arraycopy(heap,1,newHeap,0,size-1);
		heap = new int[heap.length];
		size = 0;
		for (int i : newHeap) {
			offer(i);
		}
		return top;
	}

	public int peek(){
		return heap[0];
	}

	public static void main(String[] args) {
		HeapNode heapNode = new HeapNode(6);
		heapNode.offer(4);
		heapNode.offer(3);
		heapNode.offer(6);
		heapNode.offer(2);
		heapNode.offer(9);
		heapNode.offer(7);
		System.out.println(heapNode.peek());
		System.out.println(heapNode.poll());
		heapNode.offer(1);
		System.out.println(heapNode.peek());
	}
}
