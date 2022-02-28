package com.spring.demo.bean.annotation.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 使用这个配置类来代替 XML 文件中的 <context:component-scan base-package="com.spring.demo.bean.annotation" /> 配置项
 *
 * @Configuration 的作用是 ComponentScanConfig 这个类是配置类
 */
@Configuration
@ComponentScan(basePackages = {"com.spring.demo.bean.annotation"})
public class ComponentScanConfig {
}
