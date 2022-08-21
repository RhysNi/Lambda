package com.rhys.lambda.demo9.subM;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 2:16 上午
 */
public class Husband {
    public void buyHouse() {
        System.out.println("小明在上海汤臣一品买了一套房");
    }

    public void marry(Richable richable) {
        richable.buy();
    }

    public void soHappy() {
        marry(() -> this.buyHouse());

        //使用方法引用优化 this::方法名
        marry(this::buyHouse);
    }
}
