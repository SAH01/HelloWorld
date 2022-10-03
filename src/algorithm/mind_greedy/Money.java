package algorithm.mind_greedy;

/**
 * 贪心
 * 我买了一个商品，老板要找给我99，他有面值分别为25，10，5，1的硬币数，
 * 为了找给我最少的硬币数，那么他应该怎么找？
 * 先看看该找多少个25分的，99／25＝3，那么老板只能给我3个25分的，
 * 由于还少给我24，所以还得给我2个10分的和4个1分。
 * @author Administrator
 * @date 2022-10-02 20:32
 */
public class Money {

	public void give(int[] m, int target){

		int[] results = giveMoney(m,target);
		System.out.println(target + "的找钱方案:");
		for (int i = 0; i < results.length; i++) {
			System.out.println(results[i] + "枚" + m[i] + "面值");
		}
	}

	private int[] giveMoney(int[] m, int target) {
		int length = m.length;
		int[] nums = new int[length];		// 结果
		for (int i=0;i<length;i++){
			nums[i] = target / m[i];
			target = target % m[i];
		}
		return nums;
	}

	public static void main(String[] args) {
		int[] m = {25,10,5,1};
		new Money().give(m,99);
	}
}
