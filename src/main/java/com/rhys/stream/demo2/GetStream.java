package com.rhys.stream.demo2;

import java.util.*;
import java.util.stream.Stream;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 3:18 上午
 */
public class GetStream {
    public static void main(String[] args) {
        //把集合转换为Stream流
        List<String> list = new ArrayList<>();
        Stream<String> stream = list.stream();

        //获取键,存储到一个Set集合中
        Set<String> set = new HashSet<>();
        Stream<String> stream1 = set.stream();

        Map<String, String> map = new HashMap<>();
        //获取值,存储到一个Collection集合中
        Collection<String> values = map.values();
        Stream<String> stream2 = values.stream();
        //获取键值对(键与值的映射关系 entrySet)
        Set<Map.Entry<String, String>> entries = map.entrySet();
        Stream<Map.Entry<String, String>> stream3 = entries.stream();

        //把数组转换为Stream流
        Integer[] arr = {1, 2, 3, 4, 5};
        String[] arr2 = {"a", "bb", "ccc"};
        Stream<Integer> stream4 = Stream.of(arr);
        Stream<String> stream5 = Stream.of(arr2);
    }
}
