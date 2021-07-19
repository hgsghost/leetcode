package qs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q34 implements Question {
    @Override
    public Object run() {
        /*给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
如果数组中不存在目标值 target，返回 [-1, -1]。
进阶：
你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
示例 1：
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]
示例 2：
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
示例 3：
输入：nums = [], target = 0
输出：[-1,-1]
提示：
0 <= nums.length <= 105
-109 <= nums[i] <= 109
nums 是一个非递减数组
-109 <= target <= 109
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
        int[] nums=new int[]{-1,0,2,2,3,5,9,12};
        int[] result = searchRange(nums, 2);
        for(int i:result){
            System.out.println(i);
        }
        //System.out.println(Arrays.asList(searchRange(nums,2)));
        return null;
    }
    public int[] searchRange(int[] nums, int target) {
        if(nums.length<1){
            return new int[]{-1,-1};
        }
        return binarySearch(nums, target, 0, nums.length-1);
    }
    int[] binarySearch(int[] nums,int target,int left,int right){
        if(left>=right){
            if(nums[left]==target){
                return findRange(nums,left,target);
            }else{
                return new int[]{-1,-1};
            }
        }
        int mid=(left+right)/2;
        if (nums[mid]==target){
            return findRange(nums, mid, target);
        }else if(nums[mid]<target){
            return binarySearch(nums, target, mid+1, right);
        }else{
            return binarySearch(nums, target, left, mid-1);
        }
    }
    int[] findRange(int[] nums,int index,int target){
        int left=index;
        if(index>0){
            for(int i=index;i>=0;i--){
                if(nums[i]==target){
                    left=i;
                }
            }
        }
        int right=index;
        if(index<nums.length-1){
            for(int i=index;i<nums.length;i++){
                if(nums[i]==target){
                    right=i;
                }
            }
        }
        return new int[]{left,right};
    }
}
