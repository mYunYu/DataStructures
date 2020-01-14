package com.jju.huffmancode;

import java.util.*;

public class HuffmanCode {

    public static void main(String[] args) {
        String content = "i like like like java do you like a java";
        byte[] contentBytes = content.getBytes();
        System.out.println("字符长度：" + contentBytes.length);      //40

        //数据压缩
        byte[] huffmanCodeBytes = huffmanZip(contentBytes);
        System.out.println("压缩后的huffmanCodeBytes结果是：" + Arrays.toString(huffmanCodeBytes));
        System.out.println("长度为：" + huffmanCodeBytes.length);   //17



        //数据压缩分步过程
/*
        List<Node> nodeList = getNodes(contentBytes);
        System.out.println("nodes=" + nodeList);

        //创建的赫夫曼二叉树
        System.out.println("赫夫曼树");
        Node huffmanTreeRoot = createHuffmanTree(nodeList);
        System.out.println("前序遍历：");
        preOrder(huffmanTreeRoot);

        //生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        System.out.println("生成的赫夫曼编码表" + huffmanCodes);

        //经过赫夫曼编码压缩后的字节数组
        byte[] huffmanCodeBytes = zip(contentBytes, huffmanCodes);
        System.out.println("huffmanCodeBytes=" + Arrays.toString(huffmanCodeBytes));        //17
*/
    }

    //将前面的方法封装，便于调用
    /**
     *
     * @param bytes             原始的字符串对应的字节数组
     * @return                  返回的是经过赫夫曼编码处理后的字节数组(压缩后的数组)
     */
    private static byte[] huffmanZip(byte[] bytes){
        List<Node> nodeList = getNodes(bytes);
        //根据nodes创建赫夫曼树
        Node huffmanTreeRoot = createHuffmanTree(nodeList);
        //根据赫夫曼树生成对应的赫夫曼编码
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩，得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }


    //将字符串对应的byte[] 数组，通过生成的赫夫曼编码表，返回一个赫夫曼编码表 压缩后的byte[]
    /**
     *
     * @param bytes             原始的字符串对应的byte[]
     * @param huffmanCodes      生成的赫夫曼编码map
     * @return                  返回赫夫曼编码处理后的byte[]
     * 举例：String content = "i like like like java do you like a java" =》 byte[] contentBytes = content.getBytes()
     * 返回的是 101010001010....=>对应的byte[] huffmanCodeBytes，即8位对应1个byte，放入到huffmanCodeBytes
     * huffmanCodeBytes[0] = 10101000(补码) =》 byte  [推导 10101000=》 10101000 - 1 =》 10100111(反码) =》 11011000 = -88]
     *
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes){
        //1、利用huffmanCodes编码表 将bytes 转成 赫夫曼编码对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        //遍历bytes数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }

        System.out.println("stringBuilder=" + stringBuilder.toString());

        //将stringBuilder字符串转成byte[]

        //统计返回 byte[] huffmanCodeBytes长度
        int len;
        if(stringBuilder.length() % 8 == 0){
            len = stringBuilder.length() / 8;
        }
        else{
            len = stringBuilder.length() / 8 + 1;
        }
        //创建 存储压缩后的 bytes[]
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;  //定义一个计数器，记录是第几个byte
        for (int i = 0; i < stringBuilder.length(); i += 8) {     //因为是每8位对应一个byte，所以步长+8
            String strByte;
            if(i + 8 > stringBuilder.length()){     //不够8位
                strByte = stringBuilder.substring(i);
            }
            else{
                strByte = stringBuilder.substring(i, i + 8);
            }

            //将strByte 转成一个byte，放入到huffmanCodeBytes
            huffmanCodeBytes[index] = (byte) Integer.parseInt(strByte, 2);
            index ++;
        }

        return huffmanCodeBytes;
    }


    //生成赫夫曼树对应的赫夫曼编码
    //思路：
    //1、将赫夫曼编码表存放在Map<Byte, String>形式
    //   形式如： 32( )=>01, 97(a)=>100等等
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    //2、在生成赫夫曼编码表示，需要去拼接路径，定义一个StringBuilder存储某个叶子结点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    //为了调用方便，重载getCodes
    private static Map<Byte, String> getCodes(Node root){
        if(root == null){
            return null;
        }
        //处理root的左子树
        getCodes(root.left, "0", stringBuilder);
        //处理root的右子树
        getCodes(root.right, "1", stringBuilder);

        return huffmanCodes;
    }

    /**
     * 功能：将传入的node结点的所有叶子结点的赫夫曼编码得到，并放入到huffmanCodes集合
     * @param node              传入结点，
     * @param code              路径：左子结点是0，右子结点是1
     * @param stringBuilder     用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        //将code 加入到stringBuilder2
        stringBuilder2.append(code);
        if(node != null){       //如果node == null不处理
            //判断当前node 是叶子结点还是非叶子结点
            if(node.data == null){      //非叶子结点
                //递归处理
                //向左递归
                getCodes(node.left, "0", stringBuilder2);
                //向右递归
                getCodes(node.right, "1", stringBuilder2);
            }
            else{       //说明是一个叶子结点
                //就表示找到某个叶子结点的最后
                huffmanCodes.put(node.data, stringBuilder2.toString());
            }
        }
    }

    //前序遍历
    private static void preOrder(Node root){
        if(root != null){
            root.preOrder();
        }
        else{
            System.out.println("赫夫曼树为空");
        }
    }

    /**
     *
     * @param bytes     接收字节数组
     * @return          返回的就是List 形式
     */
    private static List<Node> getNodes(byte[] bytes){
        //1、创建一个ArrayList
        List<Node> nodes = new ArrayList<>();

        //遍历bytes，统计每一个byte出现的次数 => map[key,value]
        Map<Byte, Integer> counts = new HashMap<>();
        for (byte b : bytes) {
            Integer count = counts.get(b);
            if(count == null){      //Map还没有这个字符数据，第一次
                counts.put(b, 1);
            }
            else{
                counts.put(b, count + 1);
            }
        }

        //把每一个键值对转成一个Node对象，并加入到nodes集合中
        for (Map.Entry<Byte, Integer> entry : counts.entrySet()){
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    //通过list 创建对应的赫夫曼树
    private static Node createHuffmanTree(List<Node> nodes){
        while (nodes.size() > 1){
            //排序，从小到大
            Collections.sort(nodes);
            //取出第一颗最小的二叉树
            Node leftNode = nodes.get(0);
            //取出第二颗最小的二叉树
            Node rightNode = nodes.get(1);
            //创建一颗新的二叉树，它的根节点没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;

            //将已经处理过的两颗二叉树从nodes删除
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            //将新的二叉树，加入到nodes
            nodes.add(parent);
        }
        //nodes 最后的结点就是赫夫曼树的根节点
        return nodes.get(0);
    }

}

//创建Node，带数据和权值
class Node implements Comparable<Node>{
    Byte data;      //存放数据本身，比如'a' => 97,  ' ' => 32
    int weight;     //权值，表示字符出现的次数
    Node left;      //指向左边
    Node right;     //指向右边

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }


    @Override
    public int compareTo(Node o) {
        //从小到大排序
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    //前序遍历
    public void preOrder(){
        System.out.println(this);
        if(this.left != null){
            this.left.preOrder();
        }
        if(this.right != null){
            this.right.preOrder();
        }
    }

}
