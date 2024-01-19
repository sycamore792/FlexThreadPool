package org.sycamore.dtp.server.core;

import lombok.Data;

import java.util.concurrent.*;
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

    public class DynamicThreadPoolExecutor extends ThreadPoolExecutor{

        public DynamicThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
        }

        public DynamicThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
        }

        public DynamicThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
        }

        public DynamicThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler) {
            super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
        }

        @Override
        public void execute(Runnable command) {

            super.execute(command);
        }


    }
}
