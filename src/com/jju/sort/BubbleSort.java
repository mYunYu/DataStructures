package com.jju.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//冒泡排序
//小结冒泡排序规则：
//1、一共进行数组的大小-1次大的循环
//2、每一趟排序的次数在逐渐的减少
//3、如果发现在某趟排序中，没有发生一次交换，可以提前结束冒泡排序。这就是优化
public class BubbleSort {

    public static void main(String[] args) {
//        int arr[] = {3, 9, -1, 10, -2};
//
//        //测试冒泡排序
//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));

        //测试冒泡排序的速度O(n^2)，给80000个数据，进行测试
        //创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);        //生成一个[0，8000000]数
        }

//        System.out.println("排序前");
//        System.out.println(Arrays.toString(arr));

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        System.out.println("排序前的时间：" + dateStr);

        bubbleSort(arr);

        Date date1 = new Date();
        String dateStr1 = format.format(date1);
        System.out.println("排序前的时间：" + dateStr1);

//        System.out.println("排序后");
//        System.out.println(Arrays.toString(arr));

    }

    //将前面的冒泡排序算法，封装成一个方法
    public static void bubbleSort(int[] arr){
        //冒泡排序的时间复杂度=n^2
        int temp = 0;       //临时变量，用作交换
        boolean flag = false;       //标识符变量，表示是否进行过交换
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                //如果前面的数比后面的数大，则交换
                if(arr[j] > arr[j + 1]){
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
//            System.out.println("第" + (i+1) + "趟排序后的数组");
//            System.out.println(Arrays.toString(arr));

            if(!flag){   //说明在一趟排序中，一次都没有交换过
                break;
            }
            else{
                flag = false;   //重置flag，进行下次判断
            }
        }
    }

}
