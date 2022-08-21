package com.rhys.lambda.demo1;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 4:19 下午
 */
public class FactoryImpl implements Factory {

    @Override
    public User getUser() {
        return new User();
    }
}
