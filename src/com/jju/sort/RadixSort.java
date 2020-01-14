package com.jju.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

//基数排序，可以再去看下桶排序和计数排序
//注意：会耗费比较多的内存空间
//说明：
//1、基数排序是对传统桶排序的扩展，速度很快
//2、基数排序是经典的空间换时间的方式，占用内存很大，当对海量数据排序时，容易造成OutOfMemoryError
//3、基数排序是稳定的（注：假定在待排序的记录序列中，存在多个具有相同关键字的记录，若经过排序，这些记录的相对次序保持不变，
//    即在原序列中，r[i]=r[j]，且r[i]在r[j]之前，而在排序后的序列中，r[i]仍在r[j]之前，则称这种排序算法是稳定的，否则不稳定）
//4、如果有负数的数组，则不用基数排序来进行排序，如果需要支持负数，需要另外修改代码
public class RadixSort {

    public static void main(String[] args) {
//        int arr[] = {53, 3, 542, 748, 14, 214};

        //占用内存大小：80000000 * 11 * 4 / 1024 / 1024 = 3.3G
        //创建80000个随机的数组
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int)(Math.random() * 8000000);        //生成一个[0，8000000]数
        }

        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        System.out.println("排序前的时间：" + dateStr);

        radixSort(arr);

        Date date1 = new Date();
        String dateStr1 = format.format(date1);
        System.out.println("排序前的时间：" + dateStr1);

    }

    //基数排序方法
    public static void radixSort(int[] arr){

        //最终的基数排序代码

        //1、得到数组中最大的数的位数
        int max = arr[0];       //假设第一个数就是最大数
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] > max){
                max = arr[i];
            }
        }

        //2、得到最大数是几位数
        int maxLength = (max + "").length();


        //定义一个二维数组，表示10个桶，每个桶表示一个一维数组
        //说明
        //1、二维数组包含10个一维数组
        //2、为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3、很明确，基数排序是使用空间换时间的经典算法
        int [][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放的多少个数据，可以定义一个一维数组来记录各个桶的每次放入的数据个数
        //可以理解：
        //比如：bucketElementCounts[0] 记录的就是bucket[0]桶的放入数据个数
        int [] bucketElementCounts = new int[10];


        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            //针对每个元素对应的位的值进行排序处理。如：第一次是个位，第二次是十位，第三位是百位
            for(int j = 0; j < arr.length; j ++){
                //取出每个元素的对应的位的值
                int digitOfElement = arr[j] / n % 10;
                //放入到对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement] ++;
            }
            //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
            int index = 0;
            //遍历每一桶，并将桶中的数据，放入到原数组
            for(int k = 0; k < bucketElementCounts.length; k ++){
                //如果桶中有数据，则放入到原数组中
                if(bucketElementCounts[k] != 0){        //说明bucketElementCounts桶中是有数据的
                    //循环该桶即第k个桶(即第k个一维数组)，放入
                    for(int l = 0; l < bucketElementCounts[k]; l ++){
                        //取出元素放入到arr中
                        arr[index ++] = bucket[k][l];
                    }
                }
                //第i+1轮处理后，需要将每个bucketElementCounts[k] = 0
                bucketElementCounts[k] = 0;
            }

//            System.out.println("第" + (i+1) + "轮，对个位的排序处理arr = " + Arrays.toString(arr));
        }



/*
        //定义一个二维数组，表示10个桶，每个桶表示一个一维数组
        //说明
        //1、二维数组包含10个一维数组
        //2、为了防止在放入数的时候，数据溢出，则每个一维数组(桶)，大小定为arr.length
        //3、很明确，基数排序是使用空间换时间的经典算法
        int [][] bucket = new int[10][arr.length];

        //为了记录每个桶中，实际存放的多少个数据，可以定义一个一维数组来记录各个桶的每次放入的数据个数
        //可以理解：
        //比如：bucketElementCounts[0] 记录的就是bucket[0]桶的放入数据个数
        int [] bucketElementCounts = new int[10];

        //第一轮(针对每个元素的个位进行排序处理)
        for(int j = 0; j < arr.length; j ++){
            //取出每个元素的个位的值
            int digitOfElement = arr[j] / 1 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement] ++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        int index = 0;
        //遍历每一桶，并将桶中的数据，放入到原数组
        for(int k = 0; k < bucketElementCounts.length; k ++){
            //如果桶中有数据，则放入到原数组中
            if(bucketElementCounts[k] != 0){        //说明bucketElementCounts桶中是有数据的
                //循环该桶即第k个桶(即第k个一维数组)，放入
                for(int l = 0; l < bucketElementCounts[k]; l ++){
                    //取出元素放入到arr中
                    arr[index ++] = bucket[k][l];
                }
            }
            //第一轮处理后，需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }

        System.out.println("第一轮，对个位的排序处理arr = " + Arrays.toString(arr));




        //第二轮(针对每个元素的十位进行排序处理)
        for(int j = 0; j < arr.length; j ++){
            //取出每个元素的十位的值
            int digitOfElement = arr[j] / 10 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement] ++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一桶，并将桶中的数据，放入到原数组
        for(int k = 0; k < bucketElementCounts.length; k ++){
            //如果桶中有数据，则放入到原数组中
            if(bucketElementCounts[k] != 0){        //说明bucketElementCounts桶中是有数据的
                //循环该桶即第k个桶(即第k个一维数组)，放入
                for(int l = 0; l < bucketElementCounts[k]; l ++){
                    //取出元素放入到arr中
                    arr[index ++] = bucket[k][l];
                }
            }
            //第二轮处理后，需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }

        System.out.println("第二轮，对十位的排序处理arr = " + Arrays.toString(arr));



        //第三轮(针对每个元素的百位进行排序处理)
        for(int j = 0; j < arr.length; j ++){
            //取出每个元素的百位的值
            int digitOfElement = arr[j] / 100 % 10;
            //放入到对应的桶中
            bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
            bucketElementCounts[digitOfElement] ++;
        }
        //按照这个桶的顺序(一维数组的下标依次取出数据，放入原来数组)
        index = 0;
        //遍历每一桶，并将桶中的数据，放入到原数组
        for(int k = 0; k < bucketElementCounts.length; k ++){
            //如果桶中有数据，则放入到原数组中
            if(bucketElementCounts[k] != 0){        //说明bucketElementCounts桶中是有数据的
                //循环该桶即第k个桶(即第k个一维数组)，放入
                for(int l = 0; l < bucketElementCounts[k]; l ++){
                    //取出元素放入到arr中
                    arr[index ++] = bucket[k][l];
                }
            }
            //第三轮处理后，需要将每个bucketElementCounts[k] = 0
            bucketElementCounts[k] = 0;
        }

        System.out.println("第三轮，对百位的排序处理arr = " + Arrays.toString(arr));

*/

    }

}
