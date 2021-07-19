package qs;

import org.omg.CORBA.INTERNAL;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Q752 implements Question {
    @Override
    public Object run() {
        /*你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字：
         '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' 。
         每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
        锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
        列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
        字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
        示例 1:
        输入：deadends = ["0201","0101","0102","1212","2002"], target = "0202"
        输出：6
        解释：
        可能的移动序列为 "0000" -> "1000" -> "1100" -> "1200" -> "1201" -> "1202" -> "0202"。
        注意 "0000" -> "0001" -> "0002" -> "0102" -> "0202" 这样的序列是不能解锁的，
        因为当拨动到 "0102" 时这个锁就会被锁定。
        示例 2:
        输入: deadends = ["8888"], target = "0009"
        输出：1
        解释：
        把最后一位反向旋转一次即可 "0000" -> "0009"。
        示例 3:
        输入: deadends = ["8887","8889","8878","8898","8788","8988","7888","9888"], target = "8888"
        输出：-1
        解释：
        无法旋转到目标数字且不被锁定。
        示例 4:
        输入: deadends = ["0000"], target = "8888"
        输出：-1
        提示：
        1 <= deadends.length <= 500
        deadends[i].length == 4
        target.length == 4
        target 不在 deadends 之中
        target 和 deadends[i] 仅由若干位数字组成
        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/open-the-lock
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
        //["2110","0202","1222","2221","1010"]
        //"2010"
        String[] deadends=new String[]{"2110","0202","1222","2221","1010"};
        String target = "2010";
        System.out.println(openLock(deadends,target));
        return null;
    }
    static boolean[] flag;
    static Queue<String> queue;
    public int openLock(String[] deadends, String target) {
        flag=new boolean[10000];
        queue=new LinkedList<String>();
        int step=0;
        String start="0000";

        for(int i=0;i<flag.length;i++){
            flag[i]=true;
        }
        if(deadends!=null&&deadends.length>0){
            for(int i=0;i<deadends.length;i++){
                Integer a = new Integer(deadends[i]);
                if(a==0){
                    return -1;
                }
                flag[a]=false;
            }
            flag[0]=false;
        }
        queue.offer(start);
        while(!queue.isEmpty()){
            int a=queue.size();
            for(int i=0;i<a;i++){
                String ss = queue.poll();
                if(target.equals(ss)){
                    return step;
                }
                //否则8种变换
                change(ss);
            }
            step++;
        }
            return -1;
    }
    //8种变换
    void change(String s){
        //8种变换
        for(int i=0;i<4;i++){
            String sadd = subChange(i, s, true);
            int a=new Integer(sadd);
            if(flag[a]){
                queue.offer(sadd);
                flag[a]=false;
            }
            String ssub = subChange(i, s, false);
            a=new Integer(ssub);
            if(flag[a]){
                queue.offer(ssub);
                flag[a]=false;
            }
        }
    }
    String subChange(int index, String s, boolean add){
        String pre=(index==0?"":s.substring(0,index));
        String t=s.substring(index, index+1);
        String fix=(index==3?"":s.substring(index+1,4));
        if(add){
            if("9".equals(s.substring(index,index+1))){
                return pre+"0"+fix;
            }else{
                return pre+(new Byte(t)+1)+fix;
            }
        }else{
            if("0".equals(s.substring(index,index+1))){
                return pre+"9"+fix;
            }else{
                return pre+(new Byte(t)-1)+fix;
            }
        }
    }
}
