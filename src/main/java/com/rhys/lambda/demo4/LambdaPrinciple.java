package com.rhys.lambda.demo4;

import java.util.Arrays;
import java.util.List;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 10:52 下午
 */
public class LambdaPrinciple {
    public static void main(String[] args) {
        System.setProperty("jdk.internal.lambda.dumpProxyClasses", "target/");

        List<String> list = Arrays.asList("王总", "明总", "小倪");
        list.forEach(System.out::println);
    }
}
