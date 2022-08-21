package com.rhys.stream.demo2;

import java.util.stream.Stream;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 3:27 上午
 */
public class StreamAndFilter {
    public static void main(String[] args) {
        Stream.of("张晓明", "王晓芳", "张三丰", "小明", "小芳")
                .filter(name -> name.startsWith("张"))
                .forEach(System.out::println);
    }
}
