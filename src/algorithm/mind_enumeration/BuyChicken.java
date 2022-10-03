package algorithm.mind_enumeration;

/**
 * 穷举
 *
 * 以三种鸡的个数为枚举对象,分别设为mj,gj和xj，
 * 用三种鸡的总数 （mj+gj+xj=100）和买鸡钱的总数（1/3*xj+mj*3+gj*5=100）
 * 作为判定条件，穷举各种鸡的个数。
 * @author Administrator
 * @date 2022-10-02 20:43
 */
public class BuyChicken {public static void main(String[] args) {
		int mj = 0;
		int gj = 0;
		int xj = 0;
		//公鸡每只5元 最多20只，母鸡每只3元 最多33只
		for (gj = 0;gj <= 20; gj++){
			for (mj = 0;mj <= 33; mj++){
				xj = 100 - gj - mj;
				if (xj%3 == 0 && (5*gj + 3*mj + xj/3 == 100)){
					System.out.println("总共需要买小鸡："+xj+",母鸡:"+mj+",公鸡:"+gj);
				}
			}
		}
	}
}