package com.jju.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {

    public static void main(String[] args) {
        //测试：测试数组模拟环形队列的案例
        //创建一个环形队列
        CircleArrayQueue queue = new CircleArrayQueue(4);  //说明设置4，其队列的有效数据最大是3
        char key = ' ';          //接受用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        //输出一个菜单
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("e(exit)：退出程序");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列中取出数据");
            System.out.println("h(head)：查看队列头的数据");
            key = scanner.next().charAt(0);         //接收一个字符
            switch (key){
                case 's':         //显示队列
                    queue.showQueue();
                    break;
                case 'a':         //添加数据到队列
                    System.out.println("输入一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':           //取出数据
                    try{
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':           //查看队列头的数据
                    try{
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':           //退出程序
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }

        System.out.println("程序退出");
    }

}

//使用数组模拟队列---编写一个ArrayQueue类
class CircleArrayQueue{
    private int maxSize;            //表示数组的最大容量
    private int front;              //队列头:指向队列的第一个元素，初始值front=0
    private int rear;               //队列尾：指向队列的最后一个元素的后一个位置，初始值rear=0
    private int[] arr;              //该数据用于存放数据，模拟队列

    //创建队列构造器
    public CircleArrayQueue(int arrMaxSize){
        maxSize = arrMaxSize;
        arr = new int[maxSize];
        front = 0;         //指向队列头部，分析出front是指向队列的第一个元素
        rear = 0;          //指向队列尾部，指向队列的最后一个元素的后一个位置
    }

    //判断队列是否满
    public boolean isFull(){
        return (rear + 1) % maxSize == front;
    }

    //判断队列是否为空
    public boolean isEmpty(){
        return rear == front;
    }

    //添加数据到队列
    public void addQueue(int n){
        //判断队列是否满
        if(isFull()){
            System.out.println("队列满，不能加入数据");
            return ;
        }
        //直接将数据加入
        arr[rear] = n;
        //将rear后移，这里必须考虑取模
        rear = (rear + 1) % maxSize;
    }

    //获取队列的数据，出队列
    public int getQueue(){
        //判断队列是否空
        if(isEmpty()){
            //通过抛出异常
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        // 1.先把front 对应的值保留到一个临时变量
        // 2.将front 后移,考虑取模
        // 3.将临时保存的变量返回
        int value = arr[front];
        front = (front + 1) % maxSize;
        return value;
    }

    //显示队列的所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列为空，没有数据");
            return;
        }
        //思路：从front开始遍历，遍历多少个元素
        for (int i = front; i < front + size(); i++) {
            System.out.printf("arr[%d]=%d\n", i % maxSize, arr[i % maxSize]);
        }

    }

    //求出当前队列有效数据的个数
    public int size(){
        //rear = 1
        //front = 0
        //maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }


    //显示队列的头数据，注意：不是取数据
    public int headQueue(){
        //判断
        if(isEmpty()){
            throw new RuntimeException("队列为空，没有数据");
        }
        return arr[front];
    }

}
