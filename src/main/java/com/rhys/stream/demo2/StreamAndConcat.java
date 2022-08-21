package com.rhys.stream.demo2;


import java.util.stream.Stream;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 3:27 上午
 */
public class StreamAndConcat {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("张晓明", "王晓芳", "张三丰", "小明", "小芳");

        String[] arr = {"喜羊羊", "美羊羊", "懒洋洋", "红太狼", "灰太狼"};
        Stream<String> stream1 = Stream.of(arr);

        Stream.concat(stream, stream1).forEach(System.out::println);
    }
}
