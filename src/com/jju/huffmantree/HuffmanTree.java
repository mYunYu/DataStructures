package com.jju.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HuffmanTree {

    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};

        Node root = createHuffmanTree(arr);
        preOrder(root);

    }

    //编写一个前序遍历的方法
    public static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }
        else{
            System.out.println("树是空树，不能遍历");
        }
    }

    //创建赫夫曼树的方法
    /**
     *
     * @param arr       需要创建成赫夫曼树的数组
     * @return          创建好后的赫夫曼树的root结点
     */
    public static Node createHuffmanTree(int [] arr){
        //第一步，为了操作方便
        //1、遍历arr 数组
        //2、将arr的每个元素构成一个Node
        //3、将Node放入到ArrayList中
        List<Node> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        while (nodes.size() > 1){
            //排序 从小到大
            Collections.sort(nodes);

            System.out.println("nodes = " + nodes);

            //取出根节点权值最小的两个二叉树
            //1、取出权值最小的结点（二叉树）
            Node leftNode = nodes.get(0);
            //2、取出权值第二小的结点（二叉树）
            Node rightNode = nodes.get(1);
            //3、构建一个新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            //4、从ArrayList中删除处理过的二叉树
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //5、将parent加入到nodes中
            nodes.add(parent);
        }

//        Collections.sort(nodes);
//
//        System.out.println("第一次处理后：" + nodes);

        //返回赫夫曼树的root结点
        return nodes.get(0);
    }

}

//创建结点类
//为了让Node对象支持排序Collections集合排序
//让Node实现Comparable接口
class Node implements Comparable<Node>{
    public int value;      //结点权值
    public Node left;      //指向左子结点
    public Node right;     //指向右子结点


    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }



    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        //从小到大排序，比较权值
        return this.value - o.value;
    }
}
