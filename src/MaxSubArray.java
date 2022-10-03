/**
 * @author Administrator
 * @date 2022-07-09 16:38
 */
public class MaxSubArray {
	/**
	 * 返回最大子数组和
	 * @param nums
	 * @return
	 */
	public int maxSubArray(int[] nums) {
		int maxL = nums.length;
		if(maxL == 1){
			return nums[0];
		}
		else if (maxL == 2){
			int resAdd = nums[0]+nums[1];
			int res = nums[0]>nums[1] ? nums[0] : nums[1];
			return res>resAdd ? res : resAdd;
		}
		int maxRes = nums[0];
		int tempRes = nums[0];
		for(int i = 1 ; i<maxL ; i++)
		{
			for (int j = i;j < maxL;j++)
			{
				tempRes = tempRes + nums[j];
				if(tempRes>maxRes){
					maxRes=tempRes;
				}
			}
			tempRes = 0;
		}
		return maxRes;
	}

	public static void main(String[] args) {
		MaxSubArray maxSubArray = new MaxSubArray();
		int[] nums ={-2,1,-3,4,-1,2,1,-5,4};
		int res = maxSubArray.maxSubArray(nums);
		System.out.println(res);
	}
}