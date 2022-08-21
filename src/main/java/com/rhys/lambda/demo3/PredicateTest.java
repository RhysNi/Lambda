package com.rhys.lambda.demo3;

import java.util.function.Predicate;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 7:56 下午
 */
public class PredicateTest {
    public static void main(String[] args) {
        //与
        System.out.println("-----------------普通写法-----------------");
        Predicate<String> first = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        };
        Predicate<String> second = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("d");
            }
        };
        add(first, second);
        System.out.println("-----------------普通简化写法-----------------");
        add(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        }, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("d");
            }
        });
        System.out.println("-----------------lambda写法-----------------");
        add(s -> s.contains("a"), s -> s.contains("d"));


        //或
        System.out.println("-----------------普通写法-----------------");
        Predicate<String> first1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        };
        Predicate<String> second1 = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("d");
            }
        };
        or(first1, second1);
        System.out.println("-----------------普通简化写法-----------------");
        or(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("a");
            }
        }, new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.contains("d");
            }
        });
        System.out.println("-----------------lambda写法-----------------");
        or(s -> s.contains("a"), s -> s.contains("d"));


        //非
        System.out.println("-----------------普通写法-----------------");
        Predicate<String> predicate = new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 5;
            }
        };
        negate(predicate);
        System.out.println("-----------------普通简化写法-----------------");
        negate(new Predicate<String>() {
            @Override
            public boolean test(String s) {
                return s.length() > 5;
            }
        });
        System.out.println("-----------------lambda写法-----------------");
        negate(s -> s.length() > 5);
    }

    public static void add(Predicate<String> first, Predicate<String> second) {
        System.out.println("字符串符合与规则吗：" + first.and(second).test("HelloPredicate"));
    }

    public static void or(Predicate<String> first, Predicate<String> second) {
        System.out.println("字符串符合或规则吗：" + first.or(second).test("HelloPredicate"));
    }

    public static void negate(Predicate<String> predicate) {
        System.out.println("字符串很长吗（正确结果取反的结果）：" + predicate.negate().test("Predicate"));
    }
}
