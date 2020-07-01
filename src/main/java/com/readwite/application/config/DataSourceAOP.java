package com.readwite.application.config;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * 切面配置类
 */
@Aspect
@Component
public class DataSourceAOP {
    /**
     * 只要加了@Read注解的方法就是一个切入点
     */
    @Pointcut("@annotation(com.readwite.application.config.Read)")
    public void readPointcut() {}

    /**
     * 只要加了@Write注解的方法就是一个切入点
     */
    @Pointcut("@annotation(com.readwite.application.config.Write)")
    public void writePointcut() {}

    /**
     * 配置前置通知,如果是readPoint就切换数据源为从数据库
     */
    @Before("readPointcut()")
    public void readAdvise() {
        DynamicSwitchDBTypeUtil.slave();
    }

    /**
     * 配置前置通知，如果是writePoint就切换数据源为主数据库
     */
    @Before("writePointcut()")
    public void writeAdvise() {
        DynamicSwitchDBTypeUtil.master();
    }
}
