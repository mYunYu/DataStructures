package com.jju.stack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

//完成的逆波兰计算器
//功能包括：
//1、支持 + - * / ()
//2、多位数，支持小数
//3、兼容处理，过滤任何空白字符，包括空格，制表符，换页符
public class ReversePolishMultiCalc {

    //匹配  + - * / ()运算符
    static final String SYMBOL = "\\+|-|\\*|/|\\(|\\)";

    static final String LEFT = "(";
    static final String RIGHT = ")";
    static final String ADD = "+";
    static final String MINUS = "-";
    static final String TIMES = "*";
    static final String DIVISION = "/";

    //加减 + -
    static final int LEVEL_01 = 1;
    //乘除 * /
    static final int LEVEL_02 = 2;
    //括号
    static final int LEVEL_HIGH = Integer.MAX_VALUE;

    static Stack<String> stack = new Stack<>();
    static List<String> data = Collections.synchronizedList(new ArrayList<>());




}
