package com.rhys.lambda.demo7;

import com.rhys.lambda.demo1.User;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/22 1:39 上午
 */
@FunctionalInterface
public interface UserBuilder {
    User builderUser(String name,int age);
}
