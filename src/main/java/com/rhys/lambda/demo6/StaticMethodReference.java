package com.rhys.lambda.demo6;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 1:31 上午
 */
public class StaticMethodReference {
    public static void main(String[] args) {
        int i = method(-10, (num) -> {
            return Math.abs(num);
        });
        System.out.println(i);

        //方法引用优化
        int i2 = method(-20, Math::abs);
        System.out.println(i2);
    }

    public static int method(int num, Calculable calculable) {
        return calculable.calAbs(num);
    }
}
