package com.jju.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

//使用逆b 表达式实现计算器
public class PolandNotation {

    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //思路：
        //1、初始化两个栈：运算符栈s1和储存中间结果的栈s2
        //2、从左至右扫描中缀表达式
        //3、遇到操作数时，将其压s2栈
        //4、遇到运算符时，比较其与s1栈顶运算符的优先级
            //4.1、如果s1为空，或栈顶运算符为左括号"("，则直接将此运算符入栈
            //4.2、否则，若优先级比栈顶运算符的高，也将运算符压入s1
            //4.3、否则，将s1栈顶的运算符弹出并压入到s2中，再次转到(4.1)中与s1中新的栈顶运算符相比较
        //5、遇到括号时：
            //5.1、如果是左括号"("，则直接压入s1栈
            //5.2、如果是右括号")"，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
        //6、重复步骤2至5，直到表达式的最后边
        //7、将s1中剩余的运算符依次弹出并压入s2
        //8、依次弹出s2中的元素并输出，结果的逆序即为中缀表达式对应的后缀表达式

        //说明
        //1、str = "1+((2+3)*4)-5"   ===>   1 2 3 + 4 * + 5 -
        //2、因为直接对str，进行操作，不方便，因此先将str  ===》 转成中缀形式的表达式对应的list
        //    即 "1+((2+3)*4)-5"   ===>  ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        //3、将得到的中缀表达式对应的list =》 后缀表达式对应的list
        //    即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]  ===>  ArrayList[1,2,3,+,4,*,+,5,-]
        String expression = "1+((2+3)*4)-5";
        //即 "1+((2+3)*4)-5"   ===>  ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> inFixExpressionList = toInFixExpressionList(expression);
        System.out.println("中缀表达式对应的list=" + inFixExpressionList);            //[1, +, (, (, 2, +, 3, ), *, 4, ), -, 5]

        // 即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]  ===>  ArrayList[1,2,3,+,4,*,+,5,-]
        List<String> parseSuffixExpreesionList = parseSuffixExpreesionList(inFixExpressionList);
        System.out.println("后缀表达式对应的list=" + parseSuffixExpreesionList);      //[1, 2, 3, +, 4, *, +, 5, -]

        int calculate = calculate(parseSuffixExpreesionList);
        System.out.printf("expression = %d", calculate);



        /*

        //先定义一个逆波兰表达式
        //(3+4)*5-6   =>  3 4 + 5 * 6 -
        //4 * 5 - 8 + 60 + 8 / 2  => 4 5 * 8 - 60 + 8 2 / +
        //为了方便，逆波兰表达式的数字和符号使用空格隔开
        String suffixExpression = "4 5 * 8 - 60 + 8 2 / + ";
        //思路：
        //1、先将"3 4 + 5 * 6 - "  => 放到ArrayList中
        //2、将ArrayList 传递给一个方法，遍历ArrayList 配合栈 完成计算

        List<String> rpnList = getListString(suffixExpression);
        System.out.println("rpnList=" + rpnList);

        int res = calculate(rpnList);
        System.out.println("计算的结果是=" + res);

        */
    }

    // 即ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]  ===>  ArrayList[1,2,3,+,4,*,+,5,-]
    //方法：将得到的中缀表达式对应的list =》 后缀表达式对应的list
    public static List<String> parseSuffixExpreesionList(List<String> list){
        //定义两个栈
        Stack<String> s1 = new Stack<>();       //存储符号栈
        //说明：因为stack2栈，在整个转换过程中，没有pop操作，而且后面还需要逆序输出
        //因此比较麻烦，这里就不用Stack<String>， 直接使用List<String> list2
//        Stack<String> stack2 = new Stack<>();       //存储中间结果的栈stack2
        List<String> s2 = new ArrayList<>();     //存储中间结果的List2

        //遍历list
        for (String item : list) {
            //如果是一个数，就入栈s2
            if(item.matches("\\d+")){
                s2.add(item);
            }
            //如果是左括号‘(’，则直接压入栈s1
            else if(item.equals("(")){
                s1.push(item);
            }
            //如果是右括号‘)’，则依次弹出stack1栈顶的运算符，并入s2，直到遇到左括号为止，此时将这一对括号丢弃
            else if(item.equals(")")){
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();       //将 ( 弹出s1栈，消除小括号
            }
            //当item的优先级小于等于栈顶 <= s1栈顶运算符，将s1栈顶的运算符弹出并加入到s2中，再次转到(4，1)与s1中新的栈顶运算符相比较
            //问题：缺少一个比较优先级高低的方法
            else{
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)){
                    s2.add(s1.pop());
                }
                //还需要将item压入栈中
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2中
        while (s1.size() != 0){
            s2.add(s1.pop());
        }

        return s2;      //因为是存放到list中，所以按顺序输出就是对应的后缀表达式对应的list
    }

    // 即 "1+((2+3)*4)-5"   ===>  ArrayList[1,+,(,(,2,+,3,),*,4,),-,5]
    //方法，及那个中缀表达式转成对应的list
    //  s = "1+((2+3)*4)-5"
    public static List<String> toInFixExpressionList(String s){
        //定义一个list，存放中缀表达式对应的数据
        List<String> list = new ArrayList<>();
        int i = 0;      //这是一个指针，用于遍历中缀表达式字符串
        String str;     //做多位数的拼接
        char c;         //每遍历到一个字符，就放入到c中
        do{
           //如果c是一个非数字，就需要加入到list中     数字ascil码范围  48~57
           if((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57){
               list.add(c + "");
               i ++;         //i需要后移
           }
           else{            //如果是一个数，需要考虑多位数
               str = "";        //先将str 置空  '0'[48] -> '9'[57]
               while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57){
                   str += c;        //拼接
                   i ++;
               }
               list.add(str);
           }
        }while (i < s.length());

        return list;
    }


    //将一个逆波兰表达式，依次将数据和运算符放入到 ArrayList中
    public static List<String> getListString(String suffixExpression){
        //将suffixExpression进行分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /*
     *   1、从左至右扫描，将3和4压入堆栈
     *   2、遇到+运算符号，因此弹出4和3(4为栈顶元素，3为次顶元素)，计算出3+4的值，得7，再将7入栈
     *   3、将5入栈
     *   4、接下来是*运算符，因此弹出5和7，计算出7*5=35，将35入栈
     *   5、将6入栈
     *   6、最后是-运算符，计算出35-6的值，即29，由此得出最终结果
     */
    public static int calculate(List<String> list){
        //创建一个栈
        Stack<String> stack = new Stack<>();
        //遍历 list
        for (String item : list) {
            //使用正则表达式来取出数
            if(item.matches("\\d+")){       //匹配的是多位数，直接入栈
                stack.push(item);
            }
            else {          //如果是运算符，则pop出两个数，并运算,再入栈
                int num2 = Integer.parseInt(stack.pop());
                int num1 = Integer.parseInt(stack.pop());
                int res = 0;
                if(item.equals("+")){
                    res = num1 + num2;
                }
                else if(item.equals("-")){
                    res = num1 - num2;
                }
                else if(item.equals("*")){
                    res = num1 * num2;
                }
                else if(item.equals("/")){
                    res = num1 / num2;
                }
                else{
                    throw new RuntimeException("运算符有误");
                }
                //将计算结果入栈
                stack.push(res + "");
            }
        }

        //最后留在stack中的数据就是运算结果
        return Integer.parseInt(stack.pop());
    }

}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法返回对应的优先级数字
    public static int getValue(String operation){
        int result = 0;
        switch (operation){
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }

}
