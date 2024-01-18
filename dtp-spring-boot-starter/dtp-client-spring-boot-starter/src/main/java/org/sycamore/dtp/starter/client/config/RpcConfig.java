package org.sycamore.dtp.starter.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.sycamore.dtp.rpc.client.RpcProxy;
import org.sycamore.dtp.common.remote.RpcService;
import org.sycamore.dtp.starter.rpc.client.RpcFactoryBean;


/**
 * THIS IS A CLASS
 *
 * @PROJECT_NAME: dtp
 * @CLASS_NAME: RpcConfig
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/17 16:04
 */
@Configuration
@ComponentScan("org.sycamore.dtp.starter.client.controller")
public class RpcConfig {


    @Bean
    public RpcFactoryBean<RpcService> myRemoteServiceRpcFactoryBean(RpcProxy rpcProxy) {
        return new RpcFactoryBean<RpcService>(RpcService.class, rpcProxy);
    }
}
