package com.rhys.lambda.demo3;

import java.util.function.Function;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 8:30 下午
 */
public class FunctionTest {
    public static void main(String[] args) {
        System.out.println("-----------------普通写法调用`test`-----------------");
        Function<String, Integer> first = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s) + 10;
            }
        };

        Function<Integer, Integer> second = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer s) {
                return s * 10;
            }
        };
        test(first, second);

        System.out.println("-----------------普通简化写法调用`test`-----------------");
        test(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s) + 10;
            }
        }, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer s) {
                return s * 10;
            }
        });

        System.out.println("-----------------lambda写法调用`test`-----------------");
        test(s -> Integer.parseInt(s) + 10, s -> s * 10);


        //调用andThen
        System.out.println("-----------------普通写法调用`test2`-----------------");
        String str = "Rhys-25";
        Function<String, String> first1 = new Function<String, String>() {
            @Override
            public String apply(String s) {
                return s.split("-")[1];
            }
        };

        Function<String, Integer> second1 = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return Integer.parseInt(s);
            }
        };

        Function<Integer, Integer> third = new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer s) {
                return s + 1;
            }
        };
        int i = test2(str, first1, second1, third);
        System.out.println("Rhys:" + i);

        System.out.println("-----------------普通简化写法调用`test2`-----------------");
        String str1 = "Rhys-26";
        int i1 = test2(str1,
                new Function<String, String>() {
                    @Override
                    public String apply(String s) {
                        return s.split("-")[1];
                    }
                }, new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s);
                    }
                }, new Function<Integer, Integer>() {
                    @Override
                    public Integer apply(Integer s) {
                        return s + 1;
                    }
                });
        System.out.println("Rhys:" + i1);

        System.out.println("-----------------lambda写法调用`test2`-----------------");
        String str2 = "Rhys-27";
        int i2 = test2(str2, s -> s.split("-")[1], Integer::parseInt, s -> s + 1);
        System.out.println("Rhys:" + i2);
    }

    public static void test(Function<String, Integer> first, Function<Integer, Integer> second) {
        //andThen将上一个处理的返回值作为新的参数进行处理
        System.out.println((first.andThen(second).apply("10")) + 20);
    }

    public static int test2(String str, Function<String, String> first, Function<String, Integer> second, Function<Integer, Integer> third) {
        return first.andThen(second).andThen(third).apply(str);
    }
}
