package com.rhys.lambda.demo5;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 12:55 上午
 */
public class MethodReference {
    public static void main(String[] args) {
        //传统写法
        printString(s -> {
            System.out.println(s);
        });

        //方法引用写法
        printString(System.out::println);
    }

    public static void printString(Printable printable) {
        printable.print("Hello MethodReference");
    }
}
