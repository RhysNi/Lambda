package com.rhys.lambda.demo7;

import com.rhys.lambda.demo1.User;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 1:40 上午
 */
public class ConstructorReference {
    public static void main(String[] args) {
        printName("Rhys", 25, (name, age) -> new User(name, age));
        //方法引用优化
        printName("Rhys", 26, User::new);
    }

    public static void printName(String name, int age, UserBuilder builder) {
        User user = builder.builderUser(name, age);
        System.out.println(user.getName() + ":" + user.getAge());
    }
}
