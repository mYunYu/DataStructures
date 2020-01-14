package com.jju.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//插入排序
public class InsertSort {

    public static void main(String[] args) {
//        int [] arr = {101,34,119,1,-1,89};

        //测试插入排序的速度O(n^2)，给80000个数据，进行测试
        //创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);        //生成一个[0，8000000]数
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        System.out.println("排序前的时间：" + dateStr);

        insertSort(arr);

        Date date1 = new Date();
        String dateStr1 = format.format(date1);
        System.out.println("排序前的时间：" + dateStr1);

    }

    //插入排序(从小到大排序)
    public static void insertSort(int[] arr){
        int insertVal = 0;
        int insertIndex = 0;
        for(int i = 1; i < arr.length; i ++){
            //定义待插入的数
            insertVal = arr[i];
            insertIndex = i - 1;        //即arr[1]的前面这个数的下标

            //给insertVal 找到插入的位置
            //1、insertIndex >= 0   保证在给insertVal 找插入位置时不越界
            //2、insertVal < arr[insertIndex]  说明待插入的数，还没有找到插入位置
            //3、arr[insertIndex + 1] = arr[insertIndex];   就需要将arr[insertIndex]  后移
            while(insertIndex >= 0 && insertVal < arr[insertIndex]){
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex --;
            }
            //当退出while循环时，说明插入的位置找到，insertIndex + 1
            //判断是否需要赋值
            if(insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }

//            System.out.println("第" + i + "轮插入：");
//            System.out.println(Arrays.toString(arr));
        }


/*
        //第一轮
        //原始的数组：   101， 34， 119， 1
        //第一轮排序：    34， 101， 119， 1

        //定义待插入的数
        int insertVal = arr[1];
        int insertIndex = 1 - 1;        //即arr[1]的前面这个数的下标

        //给insertVal 找到插入的位置
        //1、insertIndex >= 0   保证在给insertVal 找插入位置时不越界
        //2、insertVal < arr[insertIndex]  说明待插入的数，还没有找到插入位置
        //3、arr[insertIndex + 1] = arr[insertIndex];   就需要将arr[insertIndex]  后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;

        System.out.println("第一轮插入：");
        System.out.println(Arrays.toString(arr));


        //第二轮
        insertVal = arr[2];
        insertIndex = 2 - 1;        //即arr[1]的前面这个数的下标

        //给insertVal 找到插入的位置
        //1、insertIndex >= 0   保证在给insertVal 找插入位置时不越界
        //2、insertVal < arr[insertIndex]  说明待插入的数，还没有找到插入位置
        //3、arr[insertIndex + 1] = arr[insertIndex];   就需要将arr[insertIndex]  后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;

        System.out.println("第二轮插入：");
        System.out.println(Arrays.toString(arr));



        //第三轮
        insertVal = arr[3];
        insertIndex = 3 - 1;        //即arr[1]的前面这个数的下标

        //给insertVal 找到插入的位置
        //1、insertIndex >= 0   保证在给insertVal 找插入位置时不越界
        //2、insertVal < arr[insertIndex]  说明待插入的数，还没有找到插入位置
        //3、arr[insertIndex + 1] = arr[insertIndex];   就需要将arr[insertIndex]  后移
        while(insertIndex >= 0 && insertVal < arr[insertIndex]){
            arr[insertIndex + 1] = arr[insertIndex];
            insertIndex --;
        }
        //当退出while循环时，说明插入的位置找到，insertIndex + 1
        arr[insertIndex + 1] = insertVal;

        System.out.println("第三轮插入：");
        System.out.println(Arrays.toString(arr));

*/

    }

}
