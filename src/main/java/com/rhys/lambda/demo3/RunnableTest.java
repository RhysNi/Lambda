package com.rhys.lambda.demo3;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 6:31 下午
 */
public class RunnableTest {
    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String name = Thread.currentThread().getName();
                System.out.println("线程:" + name + " 启动");
            }
        }).start();

        //Lambda实现多线程
        new Thread(() -> {
            String name = Thread.currentThread().getName();
            System.out.println("线程:" + name + " 启动");
        }).start();
    }
}
