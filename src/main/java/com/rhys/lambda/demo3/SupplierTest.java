package com.rhys.lambda.demo3;

import java.lang.reflect.Array;
import java.util.function.Supplier;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 6:37 下午
 */
public class SupplierTest {
    public static void main(String[] args) {
        int arr[] = {1, 45, 6, 23, 76, 11};
        //普通写法
        Supplier<Integer> supplier = new Supplier<Integer>() {
            @Override
            public Integer get() {
                return getMax(arr);
            }
        };
        int max = supplier.get();
        System.out.println(max);

        //定义get方法 简化代码
        int max1 = get(new Supplier<Integer>() {
            @Override
            public Integer get() {
                return getMax(arr);
            }
        });
        System.out.println(max1);

        //lambda表达式写法
        int max2 = get(() -> getMax(arr));
        System.out.println(max2);
    }

    public static int get(Supplier<Integer> supplier) {
        return supplier.get();
    }

    public static int getMax(int[] arr) {
        int temp = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > temp) {
                temp = arr[i];
            }
        }
        return temp;
    }
}
