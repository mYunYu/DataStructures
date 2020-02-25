package com.jju.algorithm.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;

//骑士周游(马踏棋盘)回溯算法 （使用了贪心算法进行优化）
public class HorseChessboardAlgorithm {

    private static int X;   //棋盘的列数
    private static int Y;   //棋盘的行数
    //创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    //使用一个属性，标记是否棋盘的所有位置都被访问过,如果为true，表示成功
    private static boolean finished;

    public static void main(String[] args) {
        System.out.println("骑士周游算法，开始运行");
        //测试骑士周游算法是否正确
        X = 8;
        Y = 8;
        int row = 1;    //马儿初始位置的行，从1开始编号
        int column = 1; //马儿初始位置的列，从1开始编号
        //创建棋盘
        int [][] chessboard = new int[X][Y];
        visited = new boolean[X * Y]; //初始值都是false
        //测试耗时
        long start = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("共耗时：" + (end - start) + "ms");

        //输出棋盘的最后情况
        for (int [] rows : chessboard) {
            for (int step : rows) {
                System.out.print(step + "\t");
            }
            System.out.println();
        }

    }

    /**
     *  完成骑士周游问题的算法
     * @param chessboard        棋盘
     * @param row               马儿当前位置的行 从0开始
     * @param column            马儿当前位置的列 从0开始
     * @param step              是第几步，初始位置就是第一步
     */
    public static void traversalChessboard(int [][] chessboard, int row, int column, int step){
        chessboard[row][column] = step;
        visited[row * X + column] = true;       //标记该位置已经访问
        //获取当前位置可以走的下一个位置的集合
        ArrayList<Point> points = next(new Point(column, row));

        //对points进行排序，排序的规则就是对points的所有的Point对象的下一步的位置的数目，进行非递减排序
        //使用贪心算法进行优化
        sort(points);

        //遍历points
        while (!points.isEmpty()){
            //取出下一个可以走的位置
            Point point = points.remove(0);
            //判断该点是否已经访问过
            if(!visited[point.y * X + point.x]){
                traversalChessboard(chessboard, point.y, point.x, step + 1);
            }
        }
        //判断马儿是否完成了任务，使用 step 和应该走的步数比较
        //如果没有达到数量，则表示没有完成任务，将整个棋盘置为0
        //step < X * Y 成立的情况有两种
        //1、棋盘到目前位置，仍然没有走完
        //2、棋盘处于一个回溯过程
        if(step < X * Y && !finished){
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        }
        else{
            finished = true;
        }
    }

    /**
     *  根据当前位置(Point对象)，计算马儿还能走哪些位置(Point)，并放入到一个集合中(ArrayList)，最多有8个位置
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint){
        //创建一个ArrayList
        ArrayList<Point> points = new ArrayList<>();
        //创建一个Point
        Point point = new Point();
        //表示马儿可以走某个位置
        if((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0){
            points.add(new Point(point));
        }
        if((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0){
            points.add(new Point(point));
        }
        if((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0){
            points.add(new Point(point));
        }
        if((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0){
            points.add(new Point(point));
        }

        if((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y){
            points.add(new Point(point));
        }
        if((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y){
            points.add(new Point(point));
        }
        if((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y){
            points.add(new Point(point));
        }
        if((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y){
            points.add(new Point(point));
        }
        return points;
    }

    //根据当前这一步的所有的下一步的选择位置，进行非递减排序,减少回溯的可能（贪心算法）
    public static void sort(ArrayList<Point> points){
        points.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                //获取到o1的下一步的所有位置的个数
                int count1 = next(o1).size();
                int count2 = next(o2).size();
                if(count1 < count2){
                    return -1;
                }
                else if(count1 == count2){
                    return 0;
                }
                else{
                    return 1;
                }
            }
        });
    }

}
