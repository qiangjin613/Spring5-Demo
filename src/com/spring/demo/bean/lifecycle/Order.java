package com.spring.demo.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Order {
    private String name;

    public void setName(String name) {
        this.name = name;
        System.out.println("（2）调用 set() 设置属性值");
    }

    public Order() {
        System.out.println("（1）通过构造器创建 Bean 实例");
    }

    public void init() {
        System.out.println("（3）执行 Bean 中自定义的初始化方法");
    }

    public void destroy() {
        System.out.println("（5）执行 Bean 中自定义销毁方法");
    }
}

class TestBeanLifecycle {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        Order order = context.getBean("order", Order.class);
        System.out.println("（4）获取创建 Bean 实例对象");

        /* 手动调用 Bean 销毁的方法 */
        ((ClassPathXmlApplicationContext) context).close();
    }
}
/*
（1）执行无参构造器
（2）调用 set() 设置属性值
（3）执行 Bean 中自定义的初始化方法
（4）获取创建 Bean 实例对象
（5）执行 Bean 中自定义销毁方法
 */


/* --------------------- 添加 Bean 后置处理器后 --------------------- */
class MyBeanPost implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行 Bean 后置处理器的 postProcessBeforeInitialization() ");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("执行 Bean 后置处理器的 postProcessAfterInitialization() ");
        return bean;
    }
}
/*
（1）通过构造器创建 Bean 实例
（2）调用 set() 设置属性值
执行 Bean 后置处理器的 postProcessBeforeInitialization()
（3）执行 Bean 中自定义的初始化方法
执行 Bean 后置处理器的 postProcessAfterInitialization()
（4）获取创建 Bean 实例对象
（5）执行 Bean 中自定义销毁方法
 */
