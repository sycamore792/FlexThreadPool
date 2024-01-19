package org.sycamore.dtp.starter.rpc.client.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.sycamore.dtp.rpc.client.RpcClient;
import org.sycamore.dtp.rpc.client.RpcProxy;
import org.sycamore.dtp.rpc.client.RpcServerLoader;
import org.sycamore.dtp.starter.rpc.client.RpcFactoryBean;

/**
 * @CLASS_NAME: NettyRpcClientConfiguration
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/16 16:32
 */
@Configuration
public class NettyRpcClientConfiguration {
    @Value("${dtp.rpc.remote.ip}")
    private String remoteIp;
    @Value("${dtp.rpc.remote.port}")
    private int port;



    @Bean
    public RpcClient rpcClient() {
        RpcServerLoader.getInstance().load(remoteIp+":"+port);
        return new RpcClient(remoteIp, port);
    }

    @Bean
    public RpcProxy rpcProxy(RpcClient rpcClient) {
        return new RpcProxy(rpcClient);
    }

}
