import java.util.HashSet;
import java.util.Set;

/**
 * @author Administrator
 * @date 2022-07-09 16:14
 */
public class RepeatingElement {
	/**
	 * 用于返回该数组是否含有重复元素
	 * @param nums
	 * @return  boolean
	 */
	public boolean containsDuplicate(int[] nums) {
		Set<Integer> set = new HashSet<Integer>();
		for(int num : nums)
		{
			set.add(num);
		}
		if(set.size() != nums.length)
		{
			return true;//有重复
		}else{
			return false;//不重复
		}
	}
	public static void main(String[] args) {

	}
}