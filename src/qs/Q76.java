package qs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q76 implements Question {
    @Override
    public Object run() {
        /*给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
注意：
对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
如果 s 中存在这样的子串，我们保证它是唯一的答案。
示例 1：
输入：s = "ADOBECODEBANC", t = "ABC"
输出："BANC"
示例 2：
输入：s = "a", t = "a"
输出："a"
示例 3:
输入: s = "a", t = "aa"
输出: ""
解释: t 中两个字符 'a' 均应包含在 s 的子串中，
因此没有符合条件的子字符串，返回空字符串。
提示：
1 <= s.length, t.length <= 105
s 和 t 由英文字母组成
进阶：你能设计一个在 o(n) 时间内解决此问题的算法吗？
来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-window-substring
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
        String s="ADOBECAAAODEBANC";
        String t="ABCC";
        System.out.println(minWindow(s,t));
        return null;
    }
    public String minWindow(String s, String t) {
        int left=0;
        int right=0;
        if(s.length()<t.length()){
            return "";
        }
        int start=0;
        int len=-1;
        int valid=0;
        String[] sp=t.split("");
        Map<String,Integer> need=new HashMap<>();
        Map<String,Integer> window=new HashMap<>();
        for(int i=0;i<sp.length;i++){
            String sub=sp[i];
            if(need.containsKey(sub)){
                need.put(sub,need.get(sub)+1);
            }else{
                need.put(sub, 1);
            }
        }
        while(right<s.length()){
            String sub=s.substring(right,right+1);
            if(need.containsKey(sub)){
                int a=0;
                if(window.containsKey(sub)){
                    window.put(sub, a=(window.get(sub)+1));
                }else{
                    window.put(sub, a=1);
                }
                if(a<=need.get(sub)){
                    valid++;
                }
            }
            right++;

            while(valid==t.length()&&((need.containsKey(s.substring(left,left+1))&&window.get(s.substring(left,left+1))>need.get(s.substring(left,left+1)))||!need.containsKey(s.substring(left,left+1)))){
                if(need.containsKey(s.substring(left,left+1))){

                    window.put(s.substring(left,left+1), window.get(s.substring(left,left+1))-1);
                }
                left++;
            }
            if(valid==t.length()){
                int newlen=right-left;
                if(len==-1||newlen<len){
                    start=left;
                    len=newlen;
                }
            }
        }
        return len==-1?"":s.substring(start, start+len);
    }
    boolean getContains(Map<String,Integer> need,Map<String,Integer> window){
        if(need.size()>window.size()){
            return false;
        }else{
            return need.entrySet().stream().allMatch(i->i.getValue()<=window.get(i.getKey()));
        }

    };
    /*public String minWindow(String s, String t) {
        int sLen = s.length(), tLen = t.length();
        // hash 用来统计每个字符出现的次数
        // 在 t 中出现减一 ，在 s 中出现加一
        // 当 hash 中所有字符出现的次数都非负，说明匹配上了
        int[] hash = new int[128];
        for (char ch : t.toCharArray()) {
            hash[ch]--;
        }
        String ans = "";
        // left: ans 的左端位置，
        // right： ans 的右端位置
        // cnt：已经匹配上的字符数
        for (int left = 0, right = 0, cnt = 0; right < sLen; right++) {
            // 如果当前字符可以匹配到 t 中某个尚未匹配的字符，就将 cnt 加一
            if ((++hash[s.charAt(right)]) <= 0) {
                cnt++;
            }
            // 如果 t 中所有字符均已匹配上，那么试图缩紧左边界
            while (cnt == tLen && hash[s.charAt(left)] > 0) {
                hash[s.charAt(left++)]--;
            }
            // 如果 t 中所有字符均已匹配上，尝试更新 ans
            if (cnt == tLen) {
                // 只在 ans 为空字符串或者 ans 长度大于当前长度时更新
                if (ans.equals("") || ans.length() > right - left + 1) {
                    ans = s.substring(left, right + 1);
                }
            }
        }
        return ans;
    }*/


}
