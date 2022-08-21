package com.rhys.stream.demo2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 3:27 上午
 */
public class StreamAndCollect {
    public static void main(String[] args) {
        //List流式操作
        List<String> list = new ArrayList<String>();
        list.add("张晓明");
        list.add("王晓芳");
        list.add("张三丰");
        list.add("小明");
        list.add("小芳");
        //1：得到名字为3个字的流
        Stream<String> stream = list.stream()
                .filter(name -> name.length() == 3);
        //2：把使用Stream流操作完毕的数据收集到List集合中并遍历s
        stream.collect(Collectors.toList())
                .forEach(System.out::println);

        //Set流式操作
        Set<Integer> set = new HashSet<Integer>();
        set.add(10);
        set.add(20);
        set.add(30);
        set.add(33);
        set.add(35);
        //3：得到年龄大于25的流
        Stream<Integer> stream1 = set.stream()
                .filter(age -> age > 25);
        //4：把使用Stream流操作完毕的数据收集到Set集合中并遍历
        stream1.collect(Collectors.toSet())
                .forEach(System.out::println);

        //数组流式操作
        String[] strArray = {"张晓明,30", "王晓芳,35", "张三丰,33", "小明,25"};
        //5：得到字符串中年龄数据大于28的流
        Stream<String> stream2 = Stream.of(strArray)
                .filter(age -> Integer.parseInt(age.split(",")[1]) > 28);
        //6：把使用Stream流操作完毕的数据收集到Map集合中并遍历，字符串中的姓名作键，年龄作值
        stream2.collect(Collectors.toMap(name -> name.split(",")[0], age -> Integer.parseInt(age.split(",")[1])))
                .forEach((name, age) -> System.out.println(name + ":" + age));
    }
}
