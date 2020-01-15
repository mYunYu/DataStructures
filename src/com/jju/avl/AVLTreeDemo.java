package com.jju.avl;

//平衡二叉树（AVL）
//可以保证查询效率较高，是二叉排序树的升级
//需要具备以下特点：
//它是一棵空树或它的左右两个子树的高度差的绝对值不超过1，并且左右两个子树都是一颗平衡二叉树。
//平衡二叉树的常用实现方法有红黑树、AVL、替罪羊树、Treap、伸展树等
public class AVLTreeDemo {

    public static void main(String[] args) {
//        int [] arr = {4, 3, 6, 5, 7, 8};
//        int [] arr = {10, 12, 8, 9, 7, 6};
        int [] arr = {10, 11, 7, 6, 8, 9};
        //创建一个AVLTree对象
        AVLTree avlTree = new AVLTree();
        //添加结点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node(arr[i]));
        }

        //中序遍历
        System.out.println("中序遍历：");
        avlTree.infixOrder();

        System.out.println("在没有旋转(平衡)处理前");   //在添加结点的时候处理了
        System.out.println("树的高度：" + avlTree.getRoot().height());               //4 ->  3        4  ->  3
        System.out.println("树的左子树高度：" + avlTree.getRoot().leftHeight());     //1  ->  2       3  ->  2
        System.out.println("树的右子树高度：" + avlTree.getRoot().rightHeight());    //3  ->  2       1  ->  2
        System.out.println("当前根节点=" + avlTree.getRoot());
        System.out.println("根节点的左子结点 + " + avlTree.getRoot().left);
        System.out.println("根节点的右子结点 + " + avlTree.getRoot().right);


    }

}


//创建AVLTree
class AVLTree{
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



    //右旋转
    private void rightRotate(){
        Node newNode = new Node(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    //左旋转
    private void leftRotate(){
        //创建新的结点，以当前根结点的值
        Node newNode = new Node(value);

        //把新结点的左子树设置为当前结点的左子树
        newNode.left = left;
        //把新结点的右子树设置为当前结点的右子树的左子树
        newNode.right = right.left;
        //把当前结点的值替换为右子节点的值
        value = right.value;
        //把当前结点的右子树设置为当前结点的右子树的右子树
        right = right.right;
        //把当前结点的左子树（左子结点）设置为新结点
        left = newNode;
    }


    //返回左子树的高度
    public int leftHeight(){
        if(left == null){
            return 0;
        }
        return left.height();
    }

    //返回右子树的高度
    public int rightHeight(){
        if(right == null){
            return 0;
        }
        return right.height();
    }

    //返回以当前结点为根节点的树的高度
    public int height(){
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
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


        //当添加完一个结点后，如果 （右子树的高度-左子树的高度） > 1，左旋转
        if(rightHeight() - leftHeight() > 1){
            //如果右子树的左子树的高度大于右子树的右子树的高度
            if(right != null && right.leftHeight() > right.rightHeight()){
                //相对当前结点的右结点（右子树）-》右旋转
                right.rightRotate();
                //再对当前结点进行左旋转
                leftRotate();
            }
            else{
                //直接进行左旋转即可
                leftRotate();
            }
            return;
        }

        //当添加完一个结点后，如果（左子树的高度-右子树的高度） > 1，右旋转
        if(leftHeight() - rightHeight() > 1){
            //如果左子树的右子树的高度大于左子树的左子树的高度
            if(left != null && left.rightHeight() > left.leftHeight()){
                //先对当前结点的左结点（左子树）-》左旋转
                left.leftRotate();
                //再对当前结点进行右旋转
                rightRotate();
            }
            else{
                //直接进行右旋转即可
                rightRotate();
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