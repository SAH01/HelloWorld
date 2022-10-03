package algorithm.mind_search;

/**
 * @author Administrator
 * @date 2022-10-02 20:51
 */
public class MergeSort {
	/**
	 * 对传入的数组进行排序，指定开始排序的起始位置和结束位置
	 * @param array
	 * @param start
	 * @param end
	 */
	public void mergeSort(int[] array, int start, int end){
		//由于是递归，所以需要有退出条件
		if (start < end) {
			//第一步，分解，将其分成左右两部分
			int mid = (start + end) / 2;
			//第二步，解决子问题 对左右子序列 分别排序
			mergeSort(array, 0, mid);
			mergeSort(array, mid + 1, end);
			//第三步，合并
			merge(array, start, mid, end);
		}
	}

	private void merge(int[] array, int left, int mid, int right) {
		//临时存放数组
		int[] tmp = new int[array.length];
		//设置检测指针，分别指向左子序列 和 右子序列的起始 ，同时设置存放指针
		int p1 = left;
		int p2 = mid+1;
		int k = left;
		//比较子序列，结果放入tmp
		while (p1 <= mid && p2 <= right){
			if (array[p1] <= array[p2]){
				tmp[k++] = array[p1++];
			}else {
				tmp[k++] = array[p2++];
			}
		}
		//如果有未检测到的，直接添加到序列最后
		while (p1 <= mid){
			tmp[k++] = array[p1++];
		}
		while (p2 <= right){
			tmp[k++] = array[p2++];
		}
		System.arraycopy(tmp,left,array,left,right+1-left);
	}

	public static void main(String[] args) {
		MergeSort mergeSort = new MergeSort();
		int[] array = {3,9,15,7,2,5,20,10,8,1};
		mergeSort.mergeSort(array,0,array.length-1);
		for (int i : array) {
			System.out.println(i);
		}
	}
}