package com.jju.linkedlist;

//约瑟夫(Josephu)问题---环形单向链表
//约瑟夫问题为：设编号为1，2，...n的m个人围坐一圈，约定编号为k(1<=k<=n)的人从1开始报数，
// 数到m的那个人出列，它的下一位又从1开始报数，数到m的那个人又出列，依此类推，直到所有人出列为止，由此产生一个出队编号的序列
//如：
//n = 5，即有5个人
//k = 1，从第一个人开始报数
//m = 2，数2下
public class Josephu {

    public static void main(String[] args) {
        //测试:构建环形链表和遍历是否正确
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        //加入5个小孩节点
        circleSingleLinkedList.addBoy(25);
        circleSingleLinkedList.showBoy();

        //测试小孩出圈是否正确
        circleSingleLinkedList.countBoy(1, 2, 25);    //    2->4->1->5->3

    }

}

//创建一个环形单向链表
class CircleSingleLinkedList{
    //创建一个first节点，当前没有编号
    private Boy first = null;

    //添加小孩节点，构建成一个环形的链表
    //思路：
    //1、先创建第一个节点，让first指向该节点，并形成环形
    //2、后面当我们每创建一个新的节点，就把该节点，加入到已有的环形链表中即可
    public void addBoy(int nums){
        //对nums 做数据校验
        if(nums < 1){
            System.out.println("nums的值不正确");
            return;
        }
        Boy curBoy = null;      //辅助指针，帮助构建环形链表
        //使用for循环创建环形链表
        for (int i = 1; i <= nums; i++) {
            //根据编号，创建小孩节点
            Boy boy = new Boy(i);
            //如果是第一个小孩
            if(i == 1){
                first = boy;
                first.setNext(first);       //构成一个环状
                curBoy = first;         //让curBoy指向第一个小孩
            }
            else{
                curBoy.setNext(boy);    //将当前节点指向下一个节点
                boy.setNext(first);     //将下一个节点指向第一个小孩，构成环状
                curBoy = boy;           //让curBoy指向下一个节点
            }
        }
    }

    //遍历当前的环形链表
    //思路：
    //1、先让一个辅助指针(变量)curBoy，指向first节点
    //2、然后通过一个while循环遍历该环形链表即可，curBoy.next = first时结束
    public void showBoy(){
        //判断链表是否为空
        if(first == null){
            System.out.println("链表为空");
            return;
        }
        //因为first不能动，因此仍然使用一个辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.printf("小孩的编号%d \n", curBoy.getNo());
            //判断是否到链表最后
            if(curBoy.getNext() == first){      //说明已经遍历完毕
                break;
            }
            curBoy = curBoy.getNext();       //让curBoy后移
        }
    }

    //根据用户的输入，计算出小孩出圈的顺序
    //需求：
    //根据用户的输入，生成一个小孩出圈的顺序
    //n=5，即有5个人
    //k=1，从第一个人开始报数
    //m=2，数2下
    //思路：
    //1、需要创建一个辅助指针(变量)helper，事先应该指向环形链表的最后这个节点
    //补充：小孩报数前，先让first和helper移动k-1次
    //2、当小孩报数时，让first和helper指针同时的移动 m - 1 次
    //3、这是就可以将first指向的小孩节点出圈
    //  first = first.next
    //  helper.next = first
    //原来first指向的节点就没有任何引用，就会被回收
    /**
     *
     * @param startNo           表示从第几个小孩开始数数
     * @param countNum          表示数几下
     * @param nums              表示最初有多少小孩在圈中
     */
    public void countBoy(int startNo, int countNum, int nums){
        //先对数据进行校验
        if(first == null || startNo < 1 || startNo > nums){
            System.out.println("参数输入有误，请重新输入");
            return;
        }
        //创建一个辅助指针，帮助完成小孩出圈
        Boy helper = first;
        //1、需要创建一个辅助指针(变量)helper，事先应该指向环形链表的最后这个节点
        while (true){
            if(helper.getNext() == first){     //说明helper指向最后小孩节点
                break;
            }
            helper = helper.getNext();          //将helper后移
        }
        //小孩报数前，先让first和helper移动k-1次
        for (int i = 0; i < startNo - 1; i++) {
            first = first.getNext();
            helper = helper.getNext();
        }
        //2、当小孩报数时，让first和helper指针同时的移动 m - 1 次，然后出圈，
        // 这里是一个循环操作，直到圈中只有一个节点
        while (true){
            if(helper == first){        //说明圈中只有一个节点
                 break;
            }
            //让first和helper指针同时的移动 countNum - 1 次
            for (int i = 0; i < countNum - 1; i++) {
                first = first.getNext();
                helper = helper.getNext();
            }
            //这时first指向的节点，就是要出圈的小孩节点
            System.out.printf("小孩%d出圈\n", first.getNo());
            //这时将first指向的小孩节点出圈    first = first.next     helper.next = first
            first = first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩编号%d \n", first.getNo());
    }


}

//创建一个Boy类，表示一个节点
class Boy{
    private int no;     //编号
    private Boy next;   //指向下一个节点，默认null

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

