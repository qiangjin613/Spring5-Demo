package com.spring.demo.bean.annotation.di;

import org.springframework.stereotype.Repository;

@Repository
public class EmpDapImpl implements EmpDao {
    @Override
    public void add() {
        System.out.println("EmpDapImpl 的 add() 执行");
    }
}
