package org.sycamore.dtp.starter.rpc.server.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;
import org.sycamore.dtp.rpc.client.RpcClient;
import org.sycamore.dtp.rpc.client.RpcProxy;
import org.sycamore.dtp.rpc.server.RpcServer;

/**
 * @CLASS_NAME: NettyRpcClientConfiguration
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/16 16:32
 */
@Configuration
public class NettyRpcServerConfiguration {

    @Value("${rpc.server.port}")
    private int port;

    @Bean
  @Order()
    public RpcServer rpcServer(ApplicationContext applicationContext) {
        return new RpcServer(port,applicationContext);
    }

}
