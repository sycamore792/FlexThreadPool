package org.sycamore.dtp.starter.server;


import org.sycamore.dtp.common.ThreadPoolParams;
import org.sycamore.dtp.common.entity.ThreadPoolBaseParams;
import org.sycamore.dtp.common.remote.RpcService;
import org.sycamore.dtp.rpc.server.RpcNativeService;
import org.sycamore.dtp.server.core.DynamicThreadPool;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @CLASS_NAME: RpcServiceImpl
 * @DESCRIPTION: RPC方法实现类
 * @CREATER: 桑运昌
 * @DATE: 2024/1/18 15:14
 */
@RpcNativeService
public class RpcServiceImpl implements RpcService {
    @Override
    public Boolean healthCheck() {
        return DynamicThreadPoolManager.isInit();
    }

    @Override
    public List<ThreadPoolBaseParams> getThreadPoolInfoList() {
        List<ThreadPoolBaseParams> threadPoolBaseParams = DynamicThreadPoolManager.getDynamicThreadPoolMap()
                .entrySet().stream().map(entry -> {
                    DynamicThreadPool threadPool = entry.getValue();
                    return threadPool.getThreadPoolBaseParams();
                }).collect(Collectors.toList());
        return threadPoolBaseParams;
    }


}
