package com.rhys.lambda.demo3;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 7:36 下午
 */
public class ComparatorTest {
    public static void main(String[] args) {
        String[] strings = {"rhys", "o-f-o", "kfc", "mother"};

        System.out.println("-----------------普通写法-----------------");
        Comparator<String> comparator = new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        };
        Arrays.sort(strings, comparator);
        System.out.println(Arrays.toString(strings));

        System.out.println("-----------------普通简化写法-----------------");
        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
        });
        System.out.println(Arrays.toString(strings));


        System.out.println("-----------------lambda写法-----------------");
        Arrays.sort(strings, (o1, o2) -> o2.length() - o1.length());
        System.out.println(Arrays.toString(strings));
    }
}
