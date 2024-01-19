package org.sycamore.dtp.common;

import lombok.Data;

/**
 * * @CLASS_NAME: ThreadPoolParams
 * @DESCRIPTION: 线程池查询参数
 * @CREATER: 桑运昌
 * @DATE: 2024/1/20 1:56
 */
@Data
public class ThreadPoolParams {
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
     * 线程数峰值
     */
    private  Integer largestPoolSize;
}
