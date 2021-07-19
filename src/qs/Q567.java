package qs;

import java.util.LinkedList;
import java.util.Queue;

public class Q567 implements Question {
    @Override
    public Object run() {
        /*给定两个字符串 s1 和 s2，写一个函数来判断 s2 是否包含 s1 的排列。
换句话说，第一个字符串的排列之一是第二个字符串的 子串 。
示例 1：
输入: s1 = "ab" s2 = "eidbaooo"
输出: True
解释: s2 包含 s1 的排列之一 ("ba").
示例 2：
输入: s1= "ab" s2 = "eidboaoo"
输出: False
提示：
1 <= s1.length, s2.length <= 104
s1 和 s2 仅包含小写字母
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/permutation-in-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/

        System.out.println(checkInclusion("ab","eidboaoo"));
        return null;
    }
    public boolean checkInclusion(String s1, String s2) {
        if(s2.length()<s1.length()){
            return false;
        }
        int[] need=new int[128];
        int[] window=new int[128];

        for(char a:s1.toCharArray()){
            need[a]++;
        }
        int left=0;
        int len=s1.length();
        int count=0;
        while(left<=s2.length()-len){
            if(left==0){
                for(char a:s2.substring(0, len).toCharArray()){
                    if(need[a]>0&&need[a]>window[a]){
                        count++;
                    }
                    window[a]++;
                }
            }else{
                char a=s2.charAt(left-1);

                if(need[a]>0&&need[a]>=window[a]){
                    count--;
                }
                window[a]--;
                char b=s2.charAt(left+len-1);
                if(need[b]>0&&need[b]>window[b]){
                    count++;
                }
                window[b]++;
            }
            if(count==s1.length()){
                return true;
            }
            left++;
        }
        return false;
    }
}
