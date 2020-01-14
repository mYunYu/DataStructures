package com.jju.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//归并排序
public class MergeSort {

    public static void main(String[] args) {
//        int arr[] = {8, 4, 5, 7, 1, 3, 6, 2};

        //创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);        //生成一个[0，8000000]数
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        System.out.println("排序前的时间：" + dateStr);

        int temp[] = new int [arr.length] ;     //归并排序需要一个额外空间
        mergeSort(arr, 0, arr.length - 1, temp);

//        System.out.println("归并排序后：" + Arrays.toString(arr));

        Date date1 = new Date();
        String dateStr1 = format.format(date1);
        System.out.println("排序前的时间：" + dateStr1);
    }

    //分+合方法
    public static void mergeSort(int [] arr, int left, int right, int [] temp){
        if(left < right){
            int mid = (left + right) / 2;       //中间索引
            //向左递归进行分解
            mergeSort(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);

            //到合并
            merge(arr, left, mid, right, temp);
        }
    }


    //合并的方法
    /**
     *
     * @param arr           待排序的数组
     * @param left          左边有序序列的初始索引
     * @param mid           中间索引
     * @param right         右边索引
     * @param temp          做中转的数组
     */
    public static void merge(int [] arr, int left, int mid, int right, int[] temp){
//        System.out.println("合并");
        int i = left;       //初始化i，左边有序序列的初始索引
        int j = mid + 1;    //初始化j，右边有序序列的初始索引
        int t = 0;          //指向temp数组的当前索引

        //先把左右两边(有序)的数组按照规则填充到temp数组中
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right){
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，填充到temp数组中
            //然后 t++, i++
            if(arr[i] <= arr[j]){
                temp[t] = arr[i];
                t += 1;
                i += 1;
            }
            //反之，将右边有序序列的当前元素，填充到temp数组中
            else{
                temp[t] = arr[j];
                t += 1;
                j += 1;
            }
        }

        //把有剩余数据的一边的数据依次全部填充到temp中
        while(i <= mid){        //说明左边的有序序列还有剩余的元素，就全部填充到temp中
            temp[t] = arr[i];
            t += 1;
            i += 1;
        }

        while(j <= right){        //说明右边的有序序列还有剩余的元素，就全部填充到temp中
            temp[t] = arr[j];
            t += 1;
            j += 1;
        }

        //将temo数组的元素拷贝到arr中
        //注意，并不是每次都拷贝所有
        t = 0;
        int tempLeft = left;
        //第一次合并 tempLeft = 0， right = 1  //第二次合并 tempLeft = 2， right = 3  //第三次合并 tempLeft = 0， right = 3
        //最后依次 tempLeft = 0 right = 7
//        System.out.println("tempLeft=" + tempLeft + " right=" + right);
        while (tempLeft <= right){
            arr[tempLeft] = temp[t];
            t += 1;
            tempLeft += 1;
        }
    }


}
