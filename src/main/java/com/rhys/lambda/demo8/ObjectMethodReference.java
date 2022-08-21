package com.rhys.lambda.demo8;


/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 1:47 上午
 */
public class ObjectMethodReference {
    public static void main(String[] args) {
        printString(s -> {
            MethodReferenceObject referenceObject = new MethodReferenceObject();
            referenceObject.printUpperCaseString(s);
        });

        //方法引用优化
        //引用某个普通的成员方法 对象名::方法名
        MethodReferenceObject referenceObject = new MethodReferenceObject();
        printString(referenceObject::printUpperCaseString);
    }

    public static void printString(Printable printable) {
        printable.print("hello method reference");
    }
}
