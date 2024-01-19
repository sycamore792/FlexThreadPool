package org.sycamore.dtp.example.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sycamore.dtp.server.core.DynamicThreadPool;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * @CLASS_NAME: DtpConfig
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/18 14:52
 */
@Configuration
public class DtpConfig {
    @Bean
    public DynamicThreadPool dynamicThreadPool(){
        return  new DynamicThreadPool(
                "test-1",
                "test-name-1",
                2,
                5,
                10,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(200));
    }

    @Bean
    public DynamicThreadPool dynamicThreadPool1(){
        return  new DynamicThreadPool(
                "test-2",
                "test-name-2",
                2,
                10,
                10,
                TimeUnit.MINUTES,
                new LinkedBlockingQueue<>(200));
    }
}
