package org.sycamore.dtp.starter.server;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.sycamore.dtp.server.core.DynamicThreadPool;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @CLASS_NAME: DynamicThreadPoolManager
 * @DESCRIPTION: 动态线程池管理器
 * @CREATER: 桑运昌
 * @DATE: 2024/1/18 13:34
 */
@Slf4j
public class DynamicThreadPoolManager implements ApplicationContextAware {
    private static ConcurrentHashMap<String, DynamicThreadPool> dynamicThreadPoolMap = new ConcurrentHashMap<>();
    private ApplicationContext applicationContext;
    private static final AtomicBoolean INIT_FLAG = new AtomicBoolean(false);
    @PostConstruct
    public void init() {
        // 初始化dynamicThreadPoolMap
        Map<String, DynamicThreadPool> dynamicThreadPoolBeanMap = applicationContext.getBeansOfType(DynamicThreadPool.class);
        for (DynamicThreadPool dynamicThreadPool : dynamicThreadPoolBeanMap.values()) {
            DynamicThreadPool put = dynamicThreadPoolMap.put(dynamicThreadPool.getId(), dynamicThreadPool);
            if (put!= null){
                log.warn("DynamicThreadPool id重复，id: {}", dynamicThreadPool.getId());
            }
        }
        log.info("dynamicThreadPoolMap初始化完成，size: {}", dynamicThreadPoolMap.size());
        INIT_FLAG.compareAndSet(false, true);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static boolean isInit() {
        return INIT_FLAG.get();
    }

    public static  DynamicThreadPool getDynamicThreadPoolById(String id) {
        return  dynamicThreadPoolMap.get(id);
    }

    public static Map<String, DynamicThreadPool> getDynamicThreadPoolMap() {
        return dynamicThreadPoolMap;
    }
}
