package org.sycamore.dtp.starter.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.sycamore.dtp.common.remote.RpcService;
import org.sycamore.dtp.rpc.client.RpcProxy;
import org.sycamore.dtp.rpc.server.RpcServer;
import org.sycamore.dtp.starter.server.DynamicThreadPoolManager;
import org.sycamore.dtp.starter.server.RpcServiceImpl;


/**
 * @CLASS_NAME: RpcConfig
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/17 16:04
 */
@Configuration
public class ServerConfig {
    @Bean
    public RpcService rpcService(){
        return new RpcServiceImpl();
    }


    @Bean
    public DynamicThreadPoolManager dynamicThreadPoolManager() {
        return new DynamicThreadPoolManager();
    }
}
