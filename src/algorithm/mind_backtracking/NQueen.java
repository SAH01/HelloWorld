package algorithm.mind_backtracking;

/**
 * 回溯算法
 * 在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，
 * 即任意两个皇后都不能处于同一行、同一列或同一斜线上，问有多少种摆法。
 * @author Administrator
 * @date 2022-10-02 19:37
 */
public class NQueen {

	int n = 4;
	int[][] boards = new int[n][n];
	int result = 0;
	/**
	 * 放入棋子，从0开始放入
	 * @param k
	 */
	public void put(int k){
		//确定解空间，每行每列放置棋子的集合
		//搜索规则，从下一个棋子开始，进行放置
		//剪枝函数，判断棋子是否能放置(不能一列，不能在一条斜线)，能放置，就得到一个解
		//如果都不满足，则进行回溯
		if (k == n){
			//放置结束
			result++;
			//打印
			for (int i=0;i<n;i++){
				for (int j=0;j<n;j++){
					System.out.print(boards[i][j]+" ");
				}
				System.out.println();
			}
			System.out.println();
		}else{
			for (int i=0;i<n;i++){
				if (check(k,i)){
					boards[k][i] = 1;
					put(k+1);
					//不是最优解 设为0
					boards[k][i] = 0;
				}
			}
		}
	}

	private boolean check(int row, int col) {
		//检查是不是在一列上
		for (int i=0;i<row;i++){
			if (boards[i][col]==1){
				return false;
			}
		}
		//左斜杠上
		for(int i=row-1, j=col-1; i>=0 && j >= 0; i--, j--) {
			if (boards[i][j] == 1) {
				return false;
			}
		}
		//右斜杠上
		for(int i=row-1, j=col+1; i>=0 && j<n; i--, j++) {
			if (boards[i][j] == 1) {
				return false;
			}
		}
		return true;
	}

	public static void main(String[] args) {
		NQueen nQueen = new NQueen();
		nQueen.put(0);

		System.out.println(nQueen.result);
	}
}