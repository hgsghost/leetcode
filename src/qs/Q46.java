package qs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q46 implements Question {
    @Override
    public Object run() {
        /*给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
        示例 1：
        输入：nums = [1,2,3]
        输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
        示例 2：
        输入：nums = [0,1]
        输出：[[0,1],[1,0]]
        示例 3：
        输入：nums = [1]
        输出：[[1]]
        提示：
        1 <= nums.length <= 6
                -10 <= nums[i] <= 10
        nums 中的所有整数 互不相同

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/permutations
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
        int[] a=new int[]{1,20};
        System.out.println(permute(a));
        return null;
    }
    public static  List<List<Integer>> result=new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        if(nums.length==1){
            List list=new ArrayList();
            list.add(nums[0]);
            List list2=new ArrayList();
            list2.add(list);
            return list2;
        }
        findTrack(new ArrayList<Integer>(),nums);
        return result;
    }
    public void findTrack(List<Integer> way,int[] nums){
        /*if(solution.size()==1){
            way.add(solution.remove(0));
            result.add(way);
            return;
        }*/
        if(way.size()==nums.length){
            List list=new ArrayList();
            list.addAll(way);
            result.add(list);
        }
        for(int i=0;i<nums.length;i++){
            if(nums[i]==-11){
                continue;
            }
            int num=nums[i];
            way.add(num);
            nums[i]=-11;
            findTrack(way, nums);
            way.remove((Object) num);
            nums[i]=num;
        }
    }
}
