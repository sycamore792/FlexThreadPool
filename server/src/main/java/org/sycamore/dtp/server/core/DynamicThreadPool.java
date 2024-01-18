package org.sycamore.dtp.server.core;

import lombok.Data;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @CLASS_NAME: DynamicThreadPool
 * @DESCRIPTION: 动态线程池
 * @CREATER: 桑运昌
 * @DATE: 2024/1/11 12:03
 */
@Data
public class DynamicThreadPool  {
    private String id;
    private String name;

    private ThreadPoolExecutor executor;

}
