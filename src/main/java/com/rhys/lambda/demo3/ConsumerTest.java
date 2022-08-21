package com.rhys.lambda.demo3;

import java.util.function.Consumer;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 7:10 下午
 */
public class ConsumerTest {
    public static void main(String[] args) {
        //普通写法
        System.out.println("-----------------普通写法调用`accept`-----------------");
        consumer(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });

        //lambda表达式写法
        System.out.println("-----------------lambda表达式写法调用`accept`-----------------");
        consumer(System.out::println);

        System.out.println("-----------------普通写法调用默认方法`andThen`-----------------");
        //普通写法调用默认方法`andThen`
        consumer(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toUpperCase());
            }
        }, new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s.toLowerCase());
            }
        });
        System.out.println("-----------------lambda表达式写法调用默认方法`andThen`-----------------");
        //lambda表达式写法调用默认方法`andThen`
        consumer(s -> System.out.println(s.toUpperCase()), s -> System.out.println(s.toLowerCase()));
    }

    public static void consumer(Consumer<String> param) {
        param.accept("Hello accept");
    }

    public static void consumer(Consumer<String> param1, Consumer<String> param2) {
        param1.andThen(param2).accept("Hello andThen");
    }
}
