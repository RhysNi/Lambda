package com.rhys.lambda.demo10;

import java.util.Arrays;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 2:24 上午
 */
public class ArrayMethodReference {
    public static void main(String[] args) {
        int[] array = createArray(10, length -> {
            return new int[length];
        });
        System.out.println(array.length);

        //使用方法引用优化
        //数组的方法引用有且只有构造器引用 数据类型[]::new
        int[] array1 = createArray(10, int[]::new);
        System.out.println(Arrays.toString(array1));
    }

    public static int[] createArray(int length, ArrayBuilder builder) {
        return builder.builderArray(length);
    }
}
