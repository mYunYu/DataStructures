package com.jju.binarysorttree;

//二叉排序树
public class binarySortTreeDemo {

    public static void main(String[] args) {
        int [] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BinarySortTree binarySortTree = new BinarySortTree();
        //循环添加结点到二叉排序树中
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node(arr[i]));
        }

        //中序遍历二叉排序树
        System.out.println("中序遍历二叉排序树：");
        binarySortTree.infixOrder();            //1，3，5，7，9，10，12


        //删除叶子结点
        binarySortTree.delNode(1);

        System.out.println("root=" + binarySortTree.getRoot());
        System.out.println("删除结点后，中序遍历二叉排序树：");
        binarySortTree.infixOrder();            //1，3，5，7，9，10，12
    }

}

//创建二叉排序树
class BinarySortTree{
    private Node root;


    public Node getRoot() {
        return root;
    }

    //查找要删除的结点
    public Node search(int value){
        if(root == null){
            return null;
        }
        else{
            return root.search(value);
        }
    }

    //查找要删除的结点的父结点
    public Node searchParent(int value){
        if(root == null){
            return null;
        }
        else {
            return root.searchParent(value);
        }
    }

    /**
     *
     * @param node              传入的结点(当作二叉排序树的根节点)
     * @return                  返回的是以node为根节点的二叉排序树的最小结点的值
     */
    public int delRightTreeMin(Node node){
        Node target = node;
        //循环的查找左子结点，就会找到最小值
        while (target.left != null){
            target = target.left;
        }
        //这时target就会指向最小结点，删除最小结点
        delNode(target.value);
        return target.value;
    }


    //删除结点
    public void delNode(int value){
        if(root == null){
            return;
        }
        else{
            // 1、需要先去找到要删除的结点 targetNode
            Node targetNode = search(value);
            //如果没有找到要删除的结点
            if(targetNode == null){
                return;
            }
            //如果发现当前这颗二叉排序树只有一个结点
            if(root.left == null && root.right == null){
                root = null;
                return;
            }
            //2、查找targetNode的父结点
            Node parent = searchParent(value);
            //如果要删除的结点是叶子结点
            if(targetNode.left == null && targetNode.right == null){
                //判断targetNode是父结点的左子结点还是右子结点
                if(parent.left != null && parent.left.value == value){              //是左子结点
                    parent.left = null;
                }
                else if(parent.right != null && parent.right.value == value){       //是右子结点
                    parent.right = null;
                }
            }
            //删除有两颗子树的结点
            else if(targetNode.left != null && targetNode.right != null){
                int minVal = delRightTreeMin(targetNode.right);
                targetNode.value = minVal;

            }
            //删除只有一颗子树的结点
            else{
                //如果要删除的结点有左子结点
                if(targetNode.left != null){
                    if(parent != null){
                        //如果targetNode是parent的左子结点
                        if(parent.left.value == value){
                            parent.left = targetNode.left;
                        }
                        //如果targetNode是parent的右子结点
                        else {
                            parent.right = targetNode.left;
                        }
                    }
                    else{
                        root = targetNode.left;
                    }

                }
                //如果要删除的结点有右子结点
                else{
                    if(parent != null){
                        //如果targetNode是parent的左子结点
                        if(parent.left.value == value){
                            parent.left = targetNode.right;
                        }
                        //如果targetNode是parent的右子结点
                        else {
                            parent.right = targetNode.right;
                        }
                    }
                    else{
                        root = targetNode.right;
                    }

                }
            }

        }
    }


    //添加结点的方法
    public void add(Node node){
        if(root == null){       //如果root为空，则直接让root指向node
            root = node;
        }
        else{
            root.add(node);
        }
    }

    //中序遍历
    public void infixOrder(){
        if(root != null){
            root.infixOrder();
        }
        else{
            System.out.println("二叉排序树为空，不能遍历");
        }
    }
}

//创建Node结点
class Node{
    int value;
    Node left;
    Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }


    /**
     *  查找要删除的结点
     * @param value         删除结点的值
     * @return              如果找到返回该结点，否则返回null
     */
    public Node search(int value){
        if(value == this.value){         //说明找到，就是该结点
            return this;
        }
        else if(value < this.value){     //如果查找的值小于当前结点的值，则向左子树递归查找
            //如果左子结点为空，则表示找不到，返回null
            if(this.left == null){
                return null;
            }
            return this.left.search(value);
        }
        else{                           //如果查找的值不小于当前结点的值，则向右子树递归查找
            if(this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     *   查找要删除结点的父节点
     * @param value         删除结点的值
     * @return              返回的是要删除结点的父结点，如果没有就返回null
     */
    public Node searchParent(int value){
        //如果当前结点就是要删除的结点的父结点，就返回
        if((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        }
        else{
            //如果查找的值小于当前结点的值，并且当前结点的左子结点不为空，则向左递归查找
            if(value < this.value && this.left != null){
                return this.left.searchParent(value);       //向左子树递归查找
            }
            else if(value >= this.value && this.right != null){
                return this.right.searchParent(value);      //向右子树递归查找
            }
            else {
                return null;        //没有找到父节点
            }
        }
    }



    //添加结点,递归的形式添加结点，需要满足二叉排序树的要求
    public void add(Node node){
        if(node == null){
            return;
        }

        //判断传入的结点的值，和当前子树的根结点的值的关系
        if(node.value < this.value){
            if(this.left == null){      //如果当前结点左子结点为空
                this.left = node;
            }
            else {                      //如果当前结点左子结点不为空，则递归的向左子树添加
                this.left.add(node);
            }
        }
        else{           //添加的结点的值大于当前结点的值
            if(this.right == null){    //如果当前结点右子结点为空
                this.right = node;
            }
            else {                      //如果当前结点右子结点不为空，则递归的向右子树添加
                this.right.add(node);
            }
        }
    }

    //中序遍历
    public void infixOrder(){
        if(this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if(this.right != null){
            this.right.infixOrder();
        }
    }

}

