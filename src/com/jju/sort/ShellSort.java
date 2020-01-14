package com.jju.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//希尔排序(缩小增量排序)
public class ShellSort {

    public static void main(String[] args) {
//        int [] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        //测试希尔排序的速度，给80000个数据，进行测试
        //创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);        //生成一个[0，8000000]数
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        System.out.println("排序前的时间：" + dateStr);

//        shellSort(arr);             //交换法
        shellSort2(arr);            //移位法
//        System.out.println(Arrays.toString(arr));

        Date date1 = new Date();
        String dateStr1 = format.format(date1);
        System.out.println("排序前的时间：" + dateStr1);

    }


    //对交换式的希尔排序进行优化---》移位法
    public static void shellSort2(int[] arr){
        //增量gap，并逐步的缩小增量
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[j];
                if(arr[j] < arr[j - gap]){
                    while (j - gap >= 0 && temp < arr[j - gap]){
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //当退出while循环后，就给temp找到插入的位置
                    arr[j] = temp;
                }
            }

        }
    }


    //希尔排序(从小到大排序,采用交换法)
    public static void shellSort(int [] arr){
        int temp = 0;
        int count = 0;
        //循环处理
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有的元素(共有gap组，每组有2个元素，步长gap)
                for (int j = i - gap; j >= 0; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明需要交换
                    if(arr[j] > arr[j + gap]){
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }

//            System.out.println("希尔排序第" + (++count) + "轮后=" + Arrays.toString(arr));
        }

/*

        //第一轮
        //思路：将10个数据分成了5组
        for (int i = 5; i < arr.length; i++) {
            //遍历各组中所有的元素(共有五组，每组有2个元素，步长5)
            for (int j = i - 5; j >= 0; j -= 5) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if(arr[j] > arr[j + 5]){
                    temp = arr[j];
                    arr[j] = arr[j + 5];
                    arr[j + 5] = temp;
                }
            }
        }

        System.out.println("希尔排序第一轮后=" + Arrays.toString(arr));


        //第二轮
        //思路：将10个数据分成了5/2 = 2组
        for (int i = 2; i < arr.length; i++) {
            //遍历各组中所有的元素(共有五组，每组有2个元素，步长5)
            for (int j = i - 2; j >= 0; j -= 2) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if(arr[j] > arr[j + 2]){
                    temp = arr[j];
                    arr[j] = arr[j + 2];
                    arr[j + 2] = temp;
                }
            }
        }

        System.out.println("希尔排序第二轮后=" + Arrays.toString(arr));


        //第三轮
        //思路：将10个数据分成了5/2 = 2/2 = 1组
        for (int i = 1; i < arr.length; i++) {
            //遍历各组中所有的元素(共有五组，每组有2个元素，步长5)
            for (int j = i - 1; j >= 0; j -= 1) {
                //如果当前元素大于加上步长后的那个元素，说明需要交换
                if(arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }

        System.out.println("希尔排序第三轮后=" + Arrays.toString(arr));


*/
    }


}
