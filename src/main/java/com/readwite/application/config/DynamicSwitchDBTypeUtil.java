package com.readwite.application.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 动态切换数据源的工具类
 */
public class DynamicSwitchDBTypeUtil {
    /**
     * 用来存储代表数据源的对象
     *  如果是里面存储是SLAVE，代表当前线程正在使用主数据库
     *  如果是里面存储的是SLAVE,代表当前线程正在使用从数据库
     */
    private static final ThreadLocal<DBTypeEnum>  CONTEXT_HAND = new ThreadLocal<>();

    /**
     * 日志对象
     */
    private static final Logger log =  LoggerFactory.getLogger(DynamicSwitchDBTypeUtil.class);

    /**
     * 切换当前线程要使用的数据源
     * @param dbTypeEnum
     */
    public static void set(DBTypeEnum dbTypeEnum) {
        CONTEXT_HAND.set(dbTypeEnum);
        log.info("切换数据源:" + dbTypeEnum);
    }

    /**
     * 切换到主数据库
     */
    public static void master() {
        set(DBTypeEnum.MASTER);
    }

    /**
     * 切换到从数据库
     */
    public static void slave() {
        /*
            目前我们只有一个从数据库，可以直接设置
            但是如果我们拥有多个从数据库那么就需要
            考虑怎么使用什么样的算法去负载均衡从数据库
         */
        set(DBTypeEnum.SLAVE);
    }

    /**
     * 移除当前线程使用的数据源
     */
    public static void remove() {
        CONTEXT_HAND.remove();
    }

    /**
     * 获取当前线程使用的枚举值
     * @return
     */
    public static DBTypeEnum get() {
        return CONTEXT_HAND.get();
    }
}


