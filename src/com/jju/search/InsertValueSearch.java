package com.jju.search;

import java.util.Arrays;

//插值查找算法
//注意：使用插值查找的前提是 该数组是有序的
//1、对于数据量比较大，关键字 分布比较均匀的查找表来说，采用插值查找，速度较快
//2、关键字分布不均匀的情况下，该方法不一定比折半查找要好
public class InsertValueSearch {

    public static void main(String[] args) {
        int [] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        System.out.println(Arrays.toString(arr));

        int index = insertValueSearch(arr, 0, arr.length - 1, 58);
        System.out.println("index=" + index);

    }


    //编写插值查找算法
    /**
     *
     * @param arr           数组
     * @param left          左边索引
     * @param right         右边索引
     * @param findVal       查找值
     * @return   如果找到，就返回对应的下标，如果没有找到，返回-1
     */
    public static int insertValueSearch(int [] arr, int left, int right, int findVal){
        System.out.println("查找次数----");

        //注意：findVal < arr[0] 和 findVal > arr[arr.length - 1] 条件必须需要，否则得到的mid值可能越界
        if(left > right || findVal < arr[0] || findVal > arr[arr.length - 1]){
            return -1;
        }

        //自适应，求出mid， mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left])
        int mid = left + (right - left) * (findVal - arr[left]) / (arr[right] - arr[left]);
        int midVal = arr[mid];
        if(findVal > midVal){           //说明应该向右边递归查找
            return insertValueSearch(arr, mid + 1, right, findVal);
        }
        else if(findVal < midVal){      //说明应该向左边递归查找
            return insertValueSearch(arr, left, mid - 1, findVal);
        }
        else{
            return mid;
        }

    }

}
