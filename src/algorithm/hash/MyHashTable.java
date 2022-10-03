package algorithm.hash;

/**
 * @author Administrator
 * @date 2022-09-09 14:28
 */
public class MyHashTable<K,V> {
	private Node<K, V>[] table;    // 实例化一个Node数组 Node数组本身又是个链表

	private static class Node<K,V>{
		final int hash; //hash值
		final K key;
		V value;
		Node<K,V> next; //下一个节点 kv对

		public Node(int hash, K key, V value, Node<K, V> next) {
			this.hash = hash;
			this.key = key;
			this.value = value;
			this.next = next;
		}
	}

	public MyHashTable(int capacity) {
		table = (Node<K,V>[]) new Node[capacity];
	}
	public void put (K key,V value){
		// 1. 计算哈希值
		int hash = hash(key);
		// 2. 计算数组的索引值【哈希表长度-1 和 索引值做与运算】
		int i = (table.length-1) & hash;
		// 3. 把当前的kv对 存到Node里
		Node<K,V> node = new Node (hash,key,value,null);
		// 4. 根据索引下标 拿到目前table里的数据 然后进行对比
		Node<K,V> kvNode = table[i];

		if(kvNode == null ){
			// 如果为空 则说明当前位置没有数据 可以直接存
			table[i] = node;
			return;
		}
		// 如果有值 就去看 key是否一样
		if(kvNode.key.equals(key))
		{
			//  如果一样 就覆盖
			kvNode.value = value;
		}
		else{
			// 如果不一样就用链表存起来
			kvNode.next = node;
		}
	}
	public V get (K key){
		int hash = hash(key);
		int i = (table.length - 1 ) & hash;
		Node<K,V> node = table[i];
		if(node == null)
		{
			return null;
		}
		Node<K,V> newNode = node;      // 做条件判断
		// 如果存在这个值 而且存在hash冲突 那就需要去循环这个链表 直到找到那个key
		while (node.next != null)
		{
			if (newNode.key.equals(key)){
				// 这就说明找到了 直接跳出循环
				break;
			}else{
				newNode=newNode.next;   // 如果找不到key 就一直往链表的后面找
			}
		}
		return newNode.value;       // 最后返回这个值
	}

	/**
	 * 计算哈希值
	 * @param key
	 * @return
	 */
	static final int hash(Object key){
		int h;
		return (key == null)? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	public static void main(String[] args) {
		MyHashTable<String,String> hashTable = new MyHashTable<String, String>(5);
		hashTable.put("key1","ycw");
		hashTable.put("key2","ycw2");
		hashTable.put("key1","ycw3");
		System.out.println(hashTable.get("key1"));
		System.out.println(hashTable.get("key2"));
	}
}