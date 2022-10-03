package algorithm.heap;

/**
 * @author Administrator
 * @date 2022-09-17 16:07
 * 从10亿个数据中找到最大的前10个？
 *
 * - 取前10个数据，构建一个小顶堆，那么根节点是最小的
 * - 然后，从数组中依次取出一个数据与堆顶比较，如果大于，替换掉堆顶元素，堆内部调整；如果小于等于堆顶，不做处理
 * - 同样逻辑，依次循环处理数组中每一个元素。
 * - 当10亿个数据处理完后，堆中的数据就是Top 10
 */
import java.util.ArrayList;
import java.util.List;

public class HeapTopK {

	public static void main(String[] args) {
		int[] nums = new int[]{1,5,4,2,3,6};
		System.out.println(topKMax(nums, 5));	//输出：[2, 3, 4, 5, 6]
	}

	//寻找前k个最大的数--使用小顶堆
	public static List<Integer> topKMax(int[] nums, int k){
		//寻找前k个最小数，因此将小顶堆大小定义为k
		HeapNode pq = new HeapNode(k);
		for(int i=0; i<nums.length; i++){
			if(i<k){
				pq.offer(nums[i]);	//前k个数，直接入堆
			}else if(nums[i]>pq.peek()){	//如果当前元素比堆顶元素大
				pq.poll();	//说明堆顶元素（堆中最小元素）一定不属于前k大的数，出堆
				pq.offer(nums[i]);	//当前元素有可能属于前k大，入堆
			}
		}

		List<Integer> ans = new ArrayList<>();
		for (int i = 0; i < k ; i++)
		{
			ans.add(pq.poll());
		}
		return ans;
	}
}