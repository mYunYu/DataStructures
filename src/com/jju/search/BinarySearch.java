package com.jju.search;

import java.util.ArrayList;
import java.util.List;

//二分查找（递归）
//注意：使用二分查找的前提是 该数组是有序的
public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = {1, 8, 10, 89, 1000,1000, 1234};

//        int resIndex = binarySearch(arr, 0, arr.length - 1, 1);
//        System.out.println("resIndex=" + resIndex);

        ArrayList<Integer> resIndexList = binarySearch2(arr, 0, arr.length - 1, 1000);
        System.out.println("resIndexList=" + resIndexList);
    }

    //完成一个课后思考题：
    //{1, 8, 10, 89, 1000, 1000, 1234} 当一个有序数组
    //有多个相同的数值时，如何将所有的数值都查找到，比如这里的1000
    //思路分析
    //1、当扎到mid 索引值，不要马上返回
    //2、向mid索引值的左边扫描，将所有满足1000 的元素的下标，加入到集合ArrayList中
    //3、向mid索引值的右边扫描，将所有满足1000 的元素的下标，加入到集合ArrayList中
    //4、将ArrayList返回
    public static ArrayList<Integer> binarySearch2(int[] arr, int left, int right, int findVal){
        System.out.println("查找次数----");

        //当left > right时，说明递归整个数组，但是没有找到
        if(left > right){
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(findVal > midVal){        //向右递归
            return binarySearch2(arr, mid + 1, right, findVal);
        }
        else if(findVal < midVal){  //向左递归
            return binarySearch2(arr, left, mid - 1, findVal);
        }
        else{
            ArrayList<Integer> resIndexList = new ArrayList<>();
            //向mid索引值的左边扫描，将所有满足1000 的元素的下标，加入到集合ArrayList中
            int temp = mid - 1;
            while (true){
                if(temp < 0 || arr[temp] != findVal){   //退出
                    break;
                }
                //否则，将temp放入到resIndexList集合中
                resIndexList.add(temp);
                temp -= 1;        //temp左移
            }
            resIndexList.add(mid);

            //向mid索引值的右边扫描，将所有满足1000 的元素的下标，加入到集合ArrayList中
            temp = mid + 1;
            while (true){
                if(temp > arr.length - 1 || arr[temp] != findVal){   //退出
                    break;
                }
                //否则，将temp放入到resIndexList集合中
                resIndexList.add(temp);
                temp += 1;        //temp右移
            }

            return resIndexList;
        }

    }

    //二分查找算法
    /**
     *
     * @param arr       数组
     * @param left      左边的索引
     * @param right     右边的索引
     * @param findVal   要查找的值
     * @return  如果找到就返回下标，如果没有找到，就返回-1
     */
    public static int binarySearch(int[] arr, int left, int right, int findVal){

        //当left > right时，说明递归整个数组，但是没有找到
        if(left > right){
            return -1;
        }
        int mid = (left + right) / 2;
        int midVal = arr[mid];

        if(findVal > midVal){        //向右递归
            return binarySearch(arr, mid + 1, right, findVal);
        }
        else if(findVal < midVal){  //向左递归
            return binarySearch(arr, left, mid - 1, findVal);
        }
        else{
            return mid;
        }

    }


}
