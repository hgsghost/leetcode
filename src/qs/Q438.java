package qs;

import java.util.ArrayList;
import java.util.List;

public class Q438 implements Question {
    @Override
    public Object run() {
        /*给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
异位词 指字母相同，但排列不同的字符串。
示例 1:

输入: s = "cbaebabacd", p = "abc"
输出: [0,6]
解释:
起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 示例 2:

输入: s = "abab", p = "ab"
输出: [0,1,2]
解释:
起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 

提示:

1 <= s.length, p.length <= 3 * 104
s 和 p 仅包含小写字母

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/find-all-anagrams-in-a-string
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/

        System.out.println(findAnagrams("cbaebabacd","abc"));
        return null;
    }
    public List<Integer> findAnagrams(String s, String p) {
        int len=p.length();
        int[] need=new int[128];
        int[] window=new int[128];
        for(char a:p.toCharArray()){
            need[a]++;
        }
        List<Integer> list=new ArrayList<>();
        int index=0;
        int count=0;
        while(index<=s.length()-len){
            if(index==0){
                for(char a:s.substring(0, len).toCharArray()){
                    if(need[a]>0&&need[a]>window[a]){
                        count++;
                    }
                    window[a]++;
                }
            }else{
                char a=s.charAt(index-1);
                if(need[a]>0&&need[a]>=window[a]){
                    count--;
                }
                window[a]--;
                char b=s.charAt(index+len-1);
                if(need[b]>0&&need[b]>window[b]){
                    count++;
                }
                window[b]++;
            }
            if(count==p.length()){
                list.add(index);
            }
            index++;
        }
        return list;
    }
}
