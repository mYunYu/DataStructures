package com.jju.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//快速排序
public class QuickSort {

    public static void main(String[] args) {
//        int [] arr = {-9, 78, 0, 23, -567, 70};

        //创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);        //生成一个[0，8000000]数
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        System.out.println("排序前的时间：" + dateStr);

        quickSort(arr, 0, arr.length - 1);
//        System.out.println(Arrays.toString(arr));

        Date date1 = new Date();
        String dateStr1 = format.format(date1);
        System.out.println("排序前的时间：" + dateStr1);

    }

    public static void quickSort(int [] arr, int left, int right){
        int l = left;       //左下标
        int r = right;      //右下标
        //pivot 中轴
        int pivot = arr[(left + right) / 2];
        int temp = 0;   //临时变量，作为交换时使用

        //while循环的目的：是让比pivot 小的值放到左边，比pivot 大的值放到右边
        while(l < r){
            //在pivot的左边一直找，找到大于等于pivot值，才退出
            while (arr[l] < pivot){
                l += 1;
            }
            //在pivot的右边一直找，找到小于等于pivot值，才退出
            while (arr[r] > pivot){
                r -= 1;
            }
            //如果l >= r说明pivot的左右两边的值，已经按照左边全部是小于等于pivot值，右边全部是大于等于pivot值
            if(l >= r){
                break;
            }

            //交换
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;

            //如果交换完后，发现这个arr[l] == pivot值相等 r--， 前移
            if(arr[l] == pivot){
                r -= 1;
            }
            //如果交换完后，发现这个arr[r] == pivot值相等 l++， 后移
            if(arr[r] == pivot){
                l += 1;
            }
        }

        //如果l == r， 必须l ++， r --，否则会出现栈溢出
        if(l == r){
            l += 1;
            r -= 1;
        }

        //向左递归
        if(left < r){
            quickSort(arr, left, r);
        }

        //向右递归
        if(right > l){
            quickSort(arr, l, right);
        }

    }



}
