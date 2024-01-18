package org.sycamore.dtp.client.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sycamore.dtp.client.remote.RpcService;
import org.sycamore.dtp.rpc.client.RpcProxy;
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
public class RpcConfig {


    @Bean
    public RpcFactoryBean<RpcService> myRemoteServiceRpcFactoryBean(RpcProxy rpcProxy) {
        return new RpcFactoryBean<RpcService>(RpcService.class, rpcProxy);
    }
}
