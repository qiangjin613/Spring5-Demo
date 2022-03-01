package com.spring.demo.bean.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.beans.PropertyDescriptor;

/**
 * 这个代码用于详细地演示 Bean 的实例化
 */
public class ZhangSan implements BeanFactoryAware, BeanNameAware, InitializingBean, DisposableBean {

    private Integer age;

    public ZhangSan() {
        System.out.println("（1）实例初始化：构造器执行");
    }

    public void setAge(Integer age) {
        this.age = age;
        System.out.println("（2）属性注入：设置属性");
    }

    public void initMethod() {
        System.out.println("（3）<bean> 的 init-method 指定的初始化方法执行");
    }

    public void destroyMethod() {
        System.out.println("（4）<bean> 的 destroy-method 指定的销毁方法执行");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryAware 接口的 setBeanFactory() 执行");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("BeanNameAware 接口的 setBeanName() 执行");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DisposableBean 接口的 destroy() 执行");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("InitializingBean 接口的 afterPropertiesSet() 执行");
    }
}

class MyBeanPostProcessor implements BeanPostProcessor {

    public MyBeanPostProcessor() {
        System.out.println("BeanPostProcessor 接口实现类 构造器执行");
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor 接口的 postProcessBeforeInitialization() 执行");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("BeanPostProcessor 接口的 postProcessAfterInitialization() 执行");
        return bean;
    }
}

class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    public MyInstantiationAwareBeanPostProcessor() {
        System.out.println("InstantiationAwareBeanPostProcessor 接口实现类 构造器执行");
    }

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor 接口的 postProcessBeforeInstantiation() 执行");
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor 接口的 postProcessAfterInstantiation() 执行");
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor 接口的 postProcessProperties() 执行");
        return pvs;
    }

    /**
     * 当 postProcessProperties() 返回为 null 时，这个方法才执行
     */
    @Override
    public PropertyValues postProcessPropertyValues(PropertyValues pvs, PropertyDescriptor[] pds, Object bean, String beanName) throws BeansException {
        System.out.println("InstantiationAwareBeanPostProcessor 接口的 postProcessPropertyValues() 执行");
        return pvs;
    }
}

class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public MyBeanFactoryPostProcessor() {
        System.out.println("BeanFactoryPostProcessor 接口实现类 构造器执行");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        System.out.println("BeanFactoryPostProcessor 接口的 postProcessBeanFactory() 执行");
    }
}

class TestBeanLifecycle {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        System.out.println("------ 获取创建 Bean 实例对象 ------");
        ZhangSan zhangSan = context.getBean("zhangSan", ZhangSan.class);
        System.out.println("------ 获取创建 Bean 实例对象 ------");

        /* 手动调用容器关闭方法，查看 Bean 的销毁 */
        ((ClassPathXmlApplicationContext) context).close();
    }
}

/*
BeanFactoryPostProcessor 接口实现类 构造器执行
BeanFactoryPostProcessor 接口的 postProcessBeanFactory() 执行
InstantiationAwareBeanPostProcessor 接口实现类 构造器执行
BeanPostProcessor 接口实现类 构造器执行

InstantiationAwareBeanPostProcessor 接口的 postProcessBeforeInstantiation() 执行
（1）实例初始化：构造器执行
InstantiationAwareBeanPostProcessor 接口的 postProcessAfterInstantiation() 执行
InstantiationAwareBeanPostProcessor 接口的 postProcessProperties() 执行

（2）属性注入：设置属性
BeanNameAware 接口的 setBeanName() 执行
BeanFactoryAware 接口的 setBeanFactory() 执行

BeanPostProcessor 接口的 postProcessBeforeInitialization() 执行
InitializingBean 接口的 afterPropertiesSet() 执行
（3）<bean> 的 init-method 指定的初始化方法执行
BeanPostProcessor 接口的 postProcessAfterInitialization() 执行

------ 获取创建 Bean 实例对象 ------
------ 获取创建 Bean 实例对象 ------

DisposableBean 接口的 destroy() 执行
（4）<bean> 的 destroy-method 指定的销毁方法执行
 */
