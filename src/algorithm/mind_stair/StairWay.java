package algorithm.mind_stair;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。每次你可以爬 1 或 2 个台阶。
 * 你有多少种不同的方法可以爬到楼顶呢？
 * @author Administrator
 * @date 2022-10-02 20:36
 */
public class StairWay {
	public int climb(int n){
		// 边界条件
		if (n == 0) return 1;
		if (n == 1) return 1;
		int result = 2;
		for (int i=2;i<=n;i++){
			result = climb(i-1) + climb(i-2);
		}
		return result;
	}

	public int climb1(int n){
		int result = 1;
		int n1 = 1;
		int n2 = 1;
		for (int i=2;i<=n;i++){
			result = n1 + n2;
			n1 = n2;
			n2 = result;
		}
		return result;

	}
	public static void main(String[] args) {
		int climb = new StairWay().climb1(4);
		System.out.println(climb);
	}
}