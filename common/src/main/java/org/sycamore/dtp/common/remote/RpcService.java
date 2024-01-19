package org.sycamore.dtp.common.remote;


import org.sycamore.dtp.common.ThreadPoolParams;
import org.sycamore.dtp.common.entity.ThreadPoolBaseParams;

import java.util.List;

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
    Boolean healthCheck();

    /**
     * 获取服务端动态线程池列表信息
     */
    List<ThreadPoolBaseParams> getThreadPoolInfoList();

}
