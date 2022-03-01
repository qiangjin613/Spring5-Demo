package com.spring.demo.aop.jdkproxy;

public class xxDaoImpl implements xxDao {
    @Override
    public int add(int a, int b) {
        System.out.println("xxDaoImpl 中的 add() 执行");
        return a + b;
    }
}
