package com.jju.recursion;

//递归调用规则：
//1、当程序执行到一个方法时，就会开辟一个独立的空间(栈)
//2、每个空间的数据(局部变量)，是独立的
public class RecursionTest {

    public static void main(String[] args) {
        //通过打印问题，回顾递归调用机制
//        test(4);

        int factorial = factorial(4);
        System.out.println("res = " + factorial);
    }

    //打印问题
    public static void test(int n) {
        if (n > 2) {
            test(n - 1);
        }
//        else{
            System.out.println("n=" + n);
//        }
    }

    //阶乘问题
    public static int factorial(int n){
        if(n == 1){
            return 1;
        }
        else{
            return factorial(n - 1) * n;
        }
    }

}
