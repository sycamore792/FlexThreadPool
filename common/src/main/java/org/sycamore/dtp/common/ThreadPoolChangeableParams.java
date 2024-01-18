package org.sycamore.dtp.common;

import lombok.Data;

/**
 * @CLASS_NAME: ThreadPoolChangeableParams
 * @DESCRIPTION: 线程池可变参数对象
 * @CREATER: 桑运昌
 * @DATE: 2024/1/18 14:11
 */
@Data
public class ThreadPoolChangeableParams {

    private Integer corePoolSize;
    private Integer maximumPoolSize;
    private Long keepAliveTime;
    private Integer queueCapacity;
}
