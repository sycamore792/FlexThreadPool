package org.sycamore.dtp.client.remote;


/**
 * @INTERFACE_NAME: RpcService
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/16 22:32
 */
public interface RpcService {


    /**
     * 检查服务端动态线程池管理器是否初始化完毕
     * @return
     */
    boolean healthCheck();
}
