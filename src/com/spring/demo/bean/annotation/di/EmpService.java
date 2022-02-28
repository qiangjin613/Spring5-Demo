package com.spring.demo.bean.annotation.di;

import com.spring.demo.bean.annotation.config.ComponentScanConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 依赖注入 使用示例
 */
@Service
public class EmpService {

    // 方式 1：使用 @Autowired 根据属性类型进行注入，不需要 set 方法
    @Autowired
    private EmpDao empDao;

    // 方式 2：使用 @Qualifier 根据属性类型进行注入，要配合 @Autowired 一起使用
    // @Qualifier的目的是当类型相同时，可以根据名称进行注入
    @Autowired
    @Qualifier("empDapImpl")
    private EmpDao empDao2;

    // 方式 3：使用 @Resource 根据属性类型进行注入
    @Resource
    private EmpDao empDao3;

    // 方式 3：使用 @Resource 根据属性名称进行注入
    @Resource(name = "empDapImpl")
    private EmpDao empDao4;

    // 方式 4：使用 @Value 注入普通属性
    @Value("123")
    private String name;

    public void add() {
        System.out.println("EmpService 的 add() 执行");
        System.out.println("name:" + name);
        empDao.add();
        empDao2.add();
        empDao3.add();
        empDao4.add();
    }
}

class Test {
    public static void main(String[] args) {
        // 除了加载 XML 中的开启组件扫描配置，还可以加载配置类来完成
        // ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml");
        ApplicationContext context = new AnnotationConfigApplicationContext(ComponentScanConfig.class);

        EmpService empService = context.getBean("empService", EmpService.class);
        empService.add();
    }
}
