package com.rhys.stream.demo1;

import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 2:44 上午
 */
public class Demo1 {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("张晓明");
        list.add("王晓芳");
        list.add("张三丰");
        list.add("小明");
        list.add("小芳");

        //传统写法
        ArrayList<String> listA = new ArrayList<>();
        for (String name : list) {
            if (name.startsWith("张")) {
                listA.add(name);
            }
        }

        ArrayList<String> listB = new ArrayList<>();
        for (String name : listA) {
            if (name.length() == 3) {
                listB.add(name);
            }
        }

        for (String name : listB) {
            System.out.println(name);
        }


        //stream优化
        Stream<String> stream = list.stream();
        //每次操作都会返回一个新的stream
        stream.filter(name -> name.startsWith("张"))
                .filter(name -> name.length() == 3)
                .forEach(System.out::println);
    }
}
