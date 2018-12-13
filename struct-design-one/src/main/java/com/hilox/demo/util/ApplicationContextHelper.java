package com.hilox.demo.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 应用上下文辅助类
 * Created by Hilox on 2018/12/11 0011.
 */
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    /**
     * 获取context
     * @return 应用上下文
     */
    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 通过name获取Bean
     * @param name 对应Bean名称
     * @return 指定Bean
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 通过class获取Bean
     * @param clazz 对应Bean类别
     * @return 指定Bean
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name和class获取Bean
     * @param name 对应Bean名称
     * @param clazz 对应Bean类别
     * @return 指定Bean
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }
}
