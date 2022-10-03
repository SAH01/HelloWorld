package algorithm.mind_recursion;

/**
 * @author Administrator
 * @date 2022-10-02 20:53
 */
public class Fibonacci {
	// 0 1 1 2 3 5 8 13 21
	public int fibonacci(int n){
		if (n == 0) return 0;
		if (n == 1) return 1;
		return fibonacci(n-1) + fibonacci(n-2);
	}

	public static void main(String[] args) {
		Fibonacci fibonacci = new Fibonacci();
		System.out.println(fibonacci.fibonacci(4));
	}
}