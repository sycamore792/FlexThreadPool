package org.sycamore.dtp.starter.server;


import org.sycamore.dtp.common.remote.RpcService;
import org.sycamore.dtp.rpc.server.RpcNativeService;

/**
 * THIS IS A CLASS
 *
 * @PROJECT_NAME: dtp
 * @CLASS_NAME: RpcServiceImpl
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/18 15:14
 */
@RpcNativeService
public class RpcServiceImpl implements RpcService {
    @Override
    public Boolean healthCheck() {
        return DynamicThreadPoolManager.isInit();
    }
}
