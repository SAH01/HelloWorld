package algorithm.mind_search;

/**
 * 又称折半查找，是一种快速查找算法，比如 有 0-100的数，
 * 让你猜数字，每猜一次，就告诉你是猜大了还是猜小了，
 * 然后再剩下的数中 继续猜，很明显符合分治的思路
 * @author Administrator
 * @date 2022-10-02 20:51
 */
public class BinarySearch {
	/**
	 * 输入数字 查找其在数组中的位置
	 * @param items
	 * @param item
	 * @return
	 */
	public int search(int[] items,int item){
		//第一步 分解，选择中间的数，将其分成两部分
		int low = 0;
		int high = items.length - 1;
//        int mid = (low+high)/2;
//        int value = items[mid];
		//第二步 解决子问题，如果比中间数小，去左边找，如果比中间数大 去右边找 相等返回成功
		while (low <= high){
			//第三步 合并
			int mid = (low + high)/2;
			int value = items[mid];
			if (item < value){
				high = mid - 1;
			}else if (item > value){
				low = mid + 1;
			}else {
				return mid;
			}
		}
		//没找到
		return -1;
	}

	public static void main(String[] args) {
		BinarySearch binarySearch = new BinarySearch();
		int[] items = {1,2,3,4,5,6};
		int search = binarySearch.search(items, 5);
		System.out.println(search);
	}
}