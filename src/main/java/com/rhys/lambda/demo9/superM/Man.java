package com.rhys.lambda.demo9.superM;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 2:05 上午
 */
public class Man extends Human {

    @Override
    public void hello() {
        System.out.println("Hello 我是村草小明");
    }

    public void method(Greetable greetable) {
        greetable.greet();
    }

    public void show() {
        //使用super成员方法引用优化 super::父类方法名
        method(super::hello);
    }
}
