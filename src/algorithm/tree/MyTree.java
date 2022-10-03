package algorithm.tree;


import sun.reflect.generics.tree.Tree;

/**
 * @author Administrator
 * @date 2022-09-16 18:18
 */
public class MyTree<E> {
	E item ;
	MyTree<E> left;
	MyTree<E> right;

	public MyTree(E item) {
		this.item = item;
	}

	public MyTree<E> buildLeft(E item){
		this.left = new MyTree<>(item);
		return this.left;
	}

	public MyTree<E> buildRight(E item){
		this.right = new MyTree<>(item);
		return this.right;
	}
	// 前序遍历
	public void prePrint(){
		System.out.print(this.item + " > ");
		myPrint(this.left);
		myPrint(this.right);
	}

	private void myPrint(MyTree<E> node) {
		if(node == null)
		{
			return;
		}

		System.out.print(node.item + " > ");
		myPrint(node.left);
		myPrint(node.right);
	}

	public static void main(String[] args) {
		MyTree<Integer> root = new MyTree<>(10);
		MyTree<Integer> left = root.buildLeft(6);
		MyTree<Integer> left1 = left.buildLeft(4);
		left1.buildLeft(2);
		left1.buildRight(8);
		MyTree<Integer> right = root.buildRight(18);
		right.buildLeft(13);
		right.buildRight(20);

		root.prePrint();
	}
}