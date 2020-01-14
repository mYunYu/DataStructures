package com.jju.linkedlist;

//双向链表
public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        //测试
        //先创建节点
        HeroNode2 heroNode1 = new HeroNode2(1, "宋江", "及时雨");
        HeroNode2 heroNode2 = new HeroNode2(2, "卢俊义", "玉麒麟");
        HeroNode2 heroNode3 = new HeroNode2(3, "吴用", "智多星");
        HeroNode2 heroNode4 = new HeroNode2(4, "林冲", "豹子头");

        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
//        doubleLinkedList.add(heroNode1);
//        doubleLinkedList.add(heroNode2);
//        doubleLinkedList.add(heroNode4);
//        doubleLinkedList.add(heroNode3);

        //加入按照编号的顺序
        doubleLinkedList.addByOrder(heroNode1);
        doubleLinkedList.addByOrder(heroNode3);
        doubleLinkedList.addByOrder(heroNode4);
        doubleLinkedList.addByOrder(heroNode2);

        doubleLinkedList.list();

        //测试修改
//        HeroNode2 heroNode5 = new HeroNode2(3, "吴用123", "智多星321");
//        doubleLinkedList.update(heroNode5);
//
//        System.out.println("修改后链表情况");
//        doubleLinkedList.list();
//
//        doubleLinkedList.del(3);
//
//        System.out.println("删除后链表情况");
//        doubleLinkedList.list();

    }

}

//创建一个双向链表的类
class DoubleLinkedList{

    //先初始化一个头节点，头节点不要动，不存放具体的数据
    private HeroNode2 head = new HeroNode2(0, "", "");

    public HeroNode2 getHead() {
        return head;
    }

    //第二种方式：根据排名添加节点到双向链表的指定位置中
    //如果有这个排名，则添加失败，并给出提示
    public void addByOrder(HeroNode2 heroNode){
        //因为头节点不能动，因此仍然通过一个辅助指针(变量)来帮助找到添加的位置
        //因为是单链表，因此找的temp 是位于 添加位置的前一个节点，否则插入不了
        HeroNode2 temp = head;
        boolean flag = false;       //flag标志，添加的编号是否存在，默认为false
        while (true){
            if(temp.next == null){      //说明temp已经在链表的最后
                break;
            }
            if(temp.next.no > heroNode.no){     //位置找到，就在temp的后面插入
                break;
            }
            else if(temp.next.no == heroNode.no){       //说明添加的节点编号已经存在
                flag = true;        //说明编号存在
                break;
            }
            temp = temp.next;       //后移，遍历当前链表
        }
        //判断flag的值
        if(flag){       //不能添加，说明编号存在
            System.out.printf("准备插入的英雄编号 %d 已经存在了，不能加入\n", heroNode.no);
        }
        else{
            //插入到链表中，temp的后面
//            heroNode.next = temp.next;
//            temp.next = heroNode;

            heroNode.next = temp.next;
            heroNode.pre = temp;
            temp.next = heroNode;
        }
    }

    //第一种方式：添加节点到双向链表（直接添加到链表的尾部）
    public void add(HeroNode2 heroNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode2 temp = head;
        //遍历链表，找到最后
        while (true){
            //找到链表的最后
            if(temp.next == null){
                break;
            }
            //如果没有找到最后，就将temp后移
            temp = temp.next;
        }
        //当退出while循环时，temp就指向了链表的最后
        //形成一个双向链表
//        temp.next = heroNode;

        temp.next = heroNode;
        heroNode.pre = temp;
    }

    //修改节点的信息，根据no编号来修改，即no编号不能改
    //说明：
    //1、根据newHeroNode  的 no 来修改即可
    public void update(HeroNode2 newHeroNode){
        //判断是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号来找
        //定义一个辅助变量temp
        HeroNode2 temp = head.next;
        boolean flag = false;       //表示是否找到该节点
        while (true){
            if(temp == null){       //说明遍历完链表
                break;
            }
            if(temp.no == newHeroNode.no){      //找到节点
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //根据flag 判断是否找到要修改的节点
        if(flag){
            temp.name = newHeroNode.name;
            temp.nickname = newHeroNode.nickname;
        }
        else {      //没有找到要修改的节点
            System.out.printf("没有找到编号 %d 的节点，不能修改\n", newHeroNode.no);
        }
    }

    //删除双向链表中的节点
    //思路
    //1、对于双向链表，可以直接找到要删除的节点
    //2、找到后，自我删除节点即可
    public void del(int no){
        //判断当前链表是否为空
        if(head.next == null){          //说明是空链表
            System.out.println("链表为空，无法删除");
            return;
        }
        HeroNode2 temp = head.next;      //辅助变量(指针)
        boolean flag = false;       //标志是否找到待删除节点
        while (true){
            if(temp == null){      //已经到链表的最后
                break;
            }
            if (temp.no == no) {       //说明找到待删除节点的前一个节点temp
                flag  = true;
                break;
            }
            temp = temp.next;           //temp后移，遍历
        }
        //判断flag
        if(flag){           //找到节点信息，可以进行删除操作
            //temp.next = temp.next.next;       单向链表删除方式
            temp.pre.next = temp.next;
            //如果是最后一个节点，就不需要执行下面这句，否则会出现空指针异常
            if(temp.next != null){
                temp.next.pre = temp.pre;
            }
        }
        else{
            System.out.printf("要删除的 %d 节点不存在\n", no);
        }
    }

    //遍历双向链表
    //显示链表[遍历]
    public void list(){
        //判断链表是否为空
        if(head.next == null){
            System.out.println("链表为空");
            return;
        }
        //因为头节点不能动，所以需要一个辅助变量来遍历
        HeroNode2 temp = head.next;
        while (true){
            //判断是否到链表最后
            if(temp == null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            //将temp后移，一定要后移，否则会是一个死循环
            temp = temp.next;
        }

    }

}

//定义HeroNode， 每个HeroNode对象就是一个节点
class HeroNode2{
    public int no;
    public String name;
    public String nickname;
    public HeroNode2 next;       //指向下一个节点，默认都为null
    public HeroNode2 pre;        //指向前一个节点，默认都为null

    //构造器
    public HeroNode2(int no, String name, String nickname){
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    //为了显示方便，重写toString方法
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }


}

