package com.rhys.stream.demo2;

import java.util.stream.Stream;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 3:27 上午
 */
public class StreamAndSkip {
    public static void main(String[] args) {
        String[] arr = {"美羊羊", "喜洋洋", "懒洋洋", "灰太狼", "红太狼"};
        Stream.of(arr).skip(2).forEach(System.out::println);
    }
}
