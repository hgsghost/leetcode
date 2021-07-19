package qs;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;

public class Q111 implements Question {
    @Override
    public Object run() {
        /*给定一个二叉树，找出其最小深度。

最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
说明：叶子节点是指没有子节点的节点。
示例 1：
输入：root = [3,9,20,null,null,15,7]
输出：2
示例 2：
输入：root = [2,null,3,null,4,null,5,null,6]
输出：5
提示：

树中节点数的范围在 [0, 105] 内
-1000 <= Node.val <= 1000

来源：力扣（LeetCode）
链接：https://leetcode-cn.com/problems/minimum-depth-of-binary-tree
著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。*/
        //[1,2,3,4,null,null,5]
        TreeNode root = new TreeNode(1);
        root.left=new TreeNode(2);
        root.right=new TreeNode(3);
        root.left.left=new TreeNode(4);
        root.right.right=new TreeNode(5);
        //root.right.right.right=new TreeNode(5);
        //root.right.right.right.right=new TreeNode(6);
        System.out.println(minDepth(root));
        return null;
    }




    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        int step=1;
        Queue<TreeNode> queueList=new LinkedList<TreeNode>();
        queueList.add(root);
        //List<TreeNode> list=new ArrayList<>();
        while(!queueList.isEmpty()){
            int a=queueList.size();
            for(int i=0;i<a;i++){
                TreeNode node=queueList.poll();
                //先判断是否是叶子节点
                if(node.left==null&&node.right==null){
                    return step;
                }
                if(node.left!=null){
                    queueList.add(node.left);
                }
                if(node.right!=null){
                    queueList.add(node.right);
                }
            }
            step++;

        }
        return -1;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
