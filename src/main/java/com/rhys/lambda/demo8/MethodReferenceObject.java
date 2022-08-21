package com.rhys.lambda.demo8;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 1:47 上午
 */
public class MethodReferenceObject {
    /**
     * 定义一个成员方法 将传入字符串格转大写输出
     *
     * @param str
     * @return void
     * @author Rhys.Ni
     * @date 2022/8/22
     */
    public int printUpperCaseString(String str) {
        System.out.println(str.toUpperCase());
        return 0;
    }
}
