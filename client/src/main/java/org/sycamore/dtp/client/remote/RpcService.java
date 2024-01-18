package org.sycamore.dtp.client.remote;


/**
 * HIS IS A INTERFACE
 *
 * @PROJECT_NAME: dtp
 * @INTERFACE_NAME: RpcService
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/16 22:32
 */
public interface RpcService {

    public  String sayHello(String name);


    /**
     * 检查服务端动态线程池管理器是否初始化完毕
     * @return
     */
    boolean healthCheck();
}
