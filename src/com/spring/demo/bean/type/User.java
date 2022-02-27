package com.spring.demo.bean.type;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class User {
    public void add() {
        System.out.println("执行 User 的 add()");
    }
}

class Test {
    public static void testAdd() {
        // 1. 加载 Spring 配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");

        // 2. 获取配置创建的对象
        User user = context.getBean("user", User.class);
        user.add();
        System.out.println(user);
    }

    public static void main(String[] args) {
        testAdd();
    }
}
