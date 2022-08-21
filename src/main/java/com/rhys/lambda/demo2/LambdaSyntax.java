package com.rhys.lambda.demo2;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 6:15 下午
 */
public class LambdaSyntax {
    public static void main(String[] args) {
        //基本格式
        IMathOperations mo = (int a, int b) -> {
            return a + b;
        };
        System.out.println(mo.operation(1, 1));

        //可省略`{}`
        IGreeting ig = (String msg) -> System.out.println("Hello" + msg);
        ig.getMsg("Rhys");

        //可省略`()`
        ig = msg -> System.out.println("Hello" + msg);
        ig.getMsg("Rhys");

        //可省略`return`
        IMathOperations mo1 = (int a, int b) -> a + b;
        System.out.println(mo1.operation(2, 2));

        //可省略`{}`和`参数类型`
        IMathOperations mo2 = (a, b) -> a + b;
        System.out.println(mo2.operation(3, 3));
    }
}
