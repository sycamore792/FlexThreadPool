package org.sycamore.dtp.common.database;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;
import org.sycamore.dtp.common.enums.QueueTypeEnum;

/**
 * THIS IS A CLASS
 *
 * @PROJECT_NAME: dtp
 * @CLASS_NAME: ThreadPoolDO
 * @DESCRIPTION:
 * @CREATER: 桑运昌
 * @DATE: 2024/1/11 15:26
 */
@Data
@Accessors(chain = true)
public class ThreadPoolDO extends BaseDO{
    /**
     * updatable
     */
    private String threadPoolName; //线程池名称
    private Integer corePoolSize; //核心线程数
    private Integer maximumPoolSize; //最大线程数
    private Integer queueCapacity; //队列容量
    private Long keepAliveTimes;  // 保活时间（毫秒）
    private boolean isAllowCoreThreadTimeOut; //是否允许核心线程超时回收

    /**
     * select-only
     */
    private Integer queueTypeEnum;//队列类型
    private Integer poolSize; //池中的线程总数
    private Integer activeCount; //正在运行中的线程数
    private Long completedTaskCount; //已完成任务数
    private Long taskCount; //总任务数
    private boolean isShutdown; //是否关闭
    private boolean isTerminated; //是否终止
    private boolean isTerminating; //是否正在终止
}
