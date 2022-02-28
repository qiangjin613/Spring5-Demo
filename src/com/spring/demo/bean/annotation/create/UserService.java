package com.spring.demo.bean.annotation.create;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 等价于 <bean id="userService" class="com.spring.demo.bean.annotation.create.UserService" />
 * 当省略 @Component 的 value 属性值时，默认 value 是 首字母小写的类名成（如 UserService --> userService）
 * 当更换为 @Service @Repository @Component 时，作用是相同的
 */
@Component("userService")
public class UserService {

    public void add() {
        System.out.println("UserService 示例的 add() 执行");
    }
}

class Test {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.add();
    }
}
