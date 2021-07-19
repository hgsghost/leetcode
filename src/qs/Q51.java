package qs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q51 implements Question {
    @Override
    public Object run() {
        /*n 
        皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。

        给你一个整数 n ，返回所有不同的 n 皇后问题 的解决方案。

        每一种解法包含一个不同的 n 皇后问题 的棋子放置方案，该方案中 'Q' 和 '.' 分别代表了皇后和空位。

        输入：n = 4
        输出：[[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
        解释：如上图所示，4 皇后问题存在两个不同的解法。
        示例 2：

        输入：n = 1
        输出：[["Q"]]

        来源：力扣（LeetCode）
        链接：https://leetcode-cn.com/problems/n-queens
        著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
        ;
        ;
        System.out.println(solveNQueens(6));
        return null;
    }
    static int a;
    static List<List<String>> result;
    public List<List<String>> solveNQueens(int n) {
        result=new ArrayList<>();
        a=n;
        String[][] qipan=new String[n][n];
        List<Integer> listi=new ArrayList<>();
        List<Integer> listj=new ArrayList<>();
        List<Integer> zhengxie=new ArrayList<>();
        List<Integer> fanxie=new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                qipan[i][j]=".";
            }
        }
        nqueen(0,qipan,listi,listj,zhengxie,fanxie);
        return result;
    }
    public void nqueen(int count,String[][] qipan,List<Integer> listi,List<Integer> listj,List<Integer> zhengxie,List<Integer> fanxie){
        if(a==count){
            createResult(qipan);
            return;
        }
        for(int i=0;i<qipan.length;i++){
            if(listi.contains(i)){
                continue;
            }
            for(int j=0;j<qipan[0].length;j++){
                if(listj.contains(j)||zhengxie.contains(i-j)||fanxie.contains(i+j)){
                    continue;
                }
                //if(isValid(i,j,qipan)){
                    qipan[i][j]="Q";
                    listi.add(i);
                    listj.add(j);
                    zhengxie.add(i-j);
                    fanxie.add(i+j);
                    nqueen(count+1, qipan,listi,listj,zhengxie,fanxie);
                    listi.remove((Object)i);
                    listj.remove((Object)j);
                    zhengxie.remove((Object)(i-j));
                    fanxie.remove((Object)(i+j));
                    qipan[i][j]=".";
                //}
            }
            break;

        }
    }
    void printQiPan(String[][] qipan){
        for(int i=0;i<qipan.length;i++){
            System.out.println("");
            for(int j=0;j<qipan[0].length;j++){
                System.out.print(qipan[i][j]);
            }
        }
        System.out.println("");
        System.out.println("----------------");
    }
    void createResult(String[][] qipan){
        String s="";
        List<String> list=new ArrayList<>();
        for(int i=0;i<qipan.length;i++){
            for(int j=0;j<qipan[0].length;j++){
                s+=qipan[i][j];
            }
            list.add(s);
            s="";
        }
        result.add(list);

    }
    boolean isValid(int i,int j,String[][] qipan){
        //先判断行
        int l=qipan.length;
        int a =i-j;
        for(int n=0;n<l;n++){
            if(n-a>=0&&n-a<l){
                if("Q".equals(qipan[n][n-a])){
                    return false;
                }
            }
            if(i-n>=0&&j+n<l&&"Q".equals(qipan[i-n][j+n])){
                return false;
            }
            if(i+n<l&&j-n>=0&&"Q".equals(qipan[i+n][j-n])){
                return false;
            }
        }
        return true;
    }
}
