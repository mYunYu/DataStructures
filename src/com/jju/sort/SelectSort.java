package com.jju.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//选择排序
public class SelectSort {

    public static void main(String[] args) {
//        int [] arr = {101,34,119,1};

        //测试选择排序的速度O(n^2)，给80000个数据，进行测试
        //创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);        //生成一个[0，8000000]数
        }

//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        System.out.println("排序前的时间：" + dateStr);

        selectSort(arr);

        Date date1 = new Date();
        String dateStr1 = format.format(date1);
        System.out.println("排序前的时间：" + dateStr1);

//        System.out.println("排序前：");
//        System.out.println(Arrays.toString(arr));
    }

    //选择排序(从小到大排序)
    public static void selectSort(int[] arr){

        //选择排序时间复杂度是O(n^2)
        for(int i = 0; i < arr.length - 1; i ++){
            int minIndex = i;
            int min = arr[i];  //假设第一个值是最小的
            for(int j = i + 1; j < arr.length; j++){
                if(min > arr[j]){       //说明假定的最小值，并不是最小
                    min = arr[j];       //重置min
                    minIndex = j;       //重置minIndex
                }
            }

            //将最小值，放在arr[0],即交换
            if(minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = min;
            }

//            System.out.println("第"+ (i+1) +"轮后：");
//            System.out.println(Arrays.toString(arr));       //1， 34， 119， 101
        }



/*
        //第一轮
        //原始的数组：   101， 34， 119， 1
        //第一轮排序：    1， 34， 119， 101
        //算法 先简单--》做复杂，就是可以把一个复杂的算法，拆分成简单的问题--》逐步解决

        //第一轮
        int minIndex = 0;
        int min = arr[0];  //假设第一个值是最小的
        for(int j = 0 + 1; j < arr.length; j++){
            if(min > arr[j]){       //说明假定的最小值，并不是最小
                min = arr[j];       //重置min
                minIndex = j;       //重置minIndex
            }
        }

        //将最小值，放在arr[0],即交换
        if(minIndex != 0){
            arr[minIndex] = arr[0];
            arr[0] = min;
        }


        System.out.println("第一轮后：");
        System.out.println(Arrays.toString(arr));       //1， 34， 119， 101



        //第二轮
        minIndex = 1;
        min = arr[1];  //假设第一个值是最小的
        for(int j = 1 + 1; j < arr.length; j++){
            if(min > arr[j]){       //说明假定的最小值，并不是最小
                min = arr[j];       //重置min
                minIndex = j;       //重置minIndex
            }
        }

        //将最小值，放在arr[0],即交换
        if(minIndex != 1){
            arr[minIndex] = arr[1];
            arr[1] = min;
        }


        System.out.println("第二轮后：");
        System.out.println(Arrays.toString(arr));       //1， 34， 119， 101



        //第三轮
        minIndex = 2;
        min = arr[2];  //假设第一个值是最小的
        for(int j = 2 + 1; j < arr.length; j++){
            if(min > arr[j]){       //说明假定的最小值，并不是最小
                min = arr[j];       //重置min
                minIndex = j;       //重置minIndex
            }
        }

        //将最小值，放在arr[0],即交换
        if(minIndex != 2){
            arr[minIndex] = arr[2];
            arr[2] = min;
        }


        System.out.println("第三轮后：");
        System.out.println(Arrays.toString(arr));       //1， 34， 119， 101

*/

    }

}
