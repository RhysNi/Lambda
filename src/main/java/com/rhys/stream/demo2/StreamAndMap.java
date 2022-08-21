package com.rhys.stream.demo2;

import java.util.stream.Stream;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 3:27 上午
 */
public class StreamAndMap {
    public static void main(String[] args) {
        Stream.of("1", "2", "3", "4").map(Integer::parseInt).forEach(System.out::println);
    }
}
