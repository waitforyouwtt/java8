package com.yidiandian.java8;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: 一点点
 * @Date: 2019/4/23 20:55
 * @Version 1.0
 * 我们有时候需要在线程中注入bean 对象，但是会出现空指针，因为web 容器并没有引用到spring 容器的对象
 * 获取spring 对象的工具类（bean对象）
 */
public class FrameSpringBeanUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext ac) throws BeansException {
        applicationContext = ac;
    }

    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }

    public static <T> T getBean(String name) {
        if (applicationContext==null) {
            System.out.println("applicationContext为空");
        }
        return (T) applicationContext.getBean(name);
    }

}
