package org.sycamore.dtp.example.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sycamore.dtp.server.core.DynamicThreadPool;

/**
 * THIS IS A CLASS
 *
 * @PROJECT_NAME: dtp
 * @CLASS_NAME: DtpConfig
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/18 14:52
 */
@Configuration
public class DtpConfig {

    @Bean
    public DynamicThreadPool dynamicThreadPool(){
        DynamicThreadPool dynamicThreadPool = new DynamicThreadPool();
        dynamicThreadPool.setId("test-id");
        dynamicThreadPool.setName("test-name");
        return dynamicThreadPool;
    }
}
