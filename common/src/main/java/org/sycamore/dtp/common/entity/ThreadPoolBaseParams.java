package org.sycamore.dtp.common.entity;

import lombok.Data;

/**
 * @CLASS_NAME: ThreadPoolBaseParams
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/20 2:33
 */
@Data
public class ThreadPoolBaseParams {
    private String id;
    private String name;
    /**
     *  线程池线程数
     */
    private Integer poolSize;
    /**
     *  最大线程数
     */
    private Integer maximumPoolSize;
    /**
     *  核心线程数
     */
    private Integer corePoolSize;

}
