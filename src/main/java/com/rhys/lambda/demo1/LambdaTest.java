package com.rhys.lambda.demo1;

/**
 * @author Rhys.Ni
 * @version 1.0
 * @date 2022/8/21 4:19 下午
 */
public class LambdaTest {
    public static void main(String[] args) {
        //子类实现接口（父类引用子类实现）
        Factory factory = new FactoryImpl();

        User user = factory.getUser();
        System.out.println(user);

        //匿名内部类
        factory = new Factory() {
            @Override
            public User getUser() {
                return new User("Rhys1", 25);
            }
        };
        User user1 = factory.getUser();
        System.out.println(user1);

        //Lambda表达式写法
        //（）-> 代替了 new Factory(){}
        //不关系实现类，不关心匿名内部类，不关心函数名
        //只关注参数列表和函数体

        // factory = () -> {
        //     return new User("Rhys2", 26);
        // };
        //可省略{};
        factory = () -> new User("Rhys2", 26);
        User user2 = factory.getUser();
        System.out.println(user2);

        //Lambda作为参数传递
        User user3 = getUserFromFactory(() -> new User("Rhys3", 29), "User");
        System.out.println(user3);

        //Lambda作为返回值
        factory = getFactory();
        System.out.println(factory.getUser());
    }

    public static User getUserFromFactory(Factory factory, String beanName) {
        if (factory != null) {
            User user = factory.getUser();
            if (user != null && user.getClass().getSimpleName().equals(beanName)) {
                return user;
            }
        }
        return null;
    }

    public static Factory getFactory() {
        return () -> new User("Rhys4", 27);
    }
}
