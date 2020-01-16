package com.jju.algorithm.dynamic;

//动态规划算法----解决背包问题
public class KnapsackProblem {

    public static void main(String[] args) {
        int [] w = {1, 4, 3};               //物品的重量
        int [] val = {1500, 3000, 2000};    //物品的价值， 这里的val[i] 就是v[i]
        int m = 4;      //背包的容量
        int n = val.length; //物品的个数
        //创建二维数组
        //v[i][j]  表示在前i个物品中能够装入容器为j的背包中的最大价值
        int [][] v = new int[n+1][m+1];

        //为了记录放入商品的情况，定义一个二位数组
        int [][] path = new int[n+1][m+1];


        //初始化第一行和第一列，在本程序中，不去处理，因为默认是0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;    //将第一列设置为0
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;    //将第一行设置为0
        }


        //根据公式来动态规划处理
        for (int i = 1; i < v.length; i++) {        //不处理第一行，i是从1开始
            for (int j = 0; j < v[0].length; j++) { //不处理第一列，j是从1开始
                //公式
                if(w[i-1] > j){       //因为程序i 是从1开始，因此原来公式中的w[i] 修改成w[i-1]
                    v[i][j] = v[i-1][j];
                }
                else{
                    //v[i][j] = Math.max(v[i-1][j], val[i-1] + v[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包情况，不能直接使用公式，需要使用if-else来处理
                    if(v[i-1][j] < val[i-1] + v[i-1][j-w[i-1]]){
                        v[i][j] = Math.max(v[i-1][j], val[i-1] + v[i-1][j-w[i-1]]);
                        //把当前的情况记录到path中
                        path[i][j] = 1;
                    }
                    else{
                        v[i][j] = v[i-1][j];
                    }
                }
            }
        }


        //输出v
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //输出最后放入的哪些商品
        int i = path.length - 1;        //行的最大下标
        int j = path[0].length - 1;     //列的最大下标
        while (i > 0 && j > 0){          //从path数组的最后开始遍历
            if(path[i][j] == 1){
                System.out.printf("将第%d个商品放入到背包\n", i);
                j -= w[i-1];
            }
            i --;
        }
    }

}
