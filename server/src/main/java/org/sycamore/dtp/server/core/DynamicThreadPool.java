package org.sycamore.dtp.server.core;

import org.sycamore.dtp.common.entity.ThreadPoolBaseParams;

import java.util.concurrent.*;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @CLASS_NAME: DynamicThreadPool
 * @DESCRIPTION: 动态线程池
 * @CREATER: 桑运昌
 * @DATE: 2024/1/11 12:03
 */
public class DynamicThreadPool {
    private String id;
    private String name;
    private volatile ThreadPoolBaseParams threadPoolBaseParams;
    private DynamicThreadPoolExecutor executor;
    public String getId() {
        return id;
    }

    public DynamicThreadPool(String id, String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        this.id = id;
        this.name = name;
        this.executor = new DynamicThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    public DynamicThreadPool(String id, String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, RejectedExecutionHandler handler) {
        this.id = id;
        this.name = name;
        this.executor = new DynamicThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, handler);
    }

    public DynamicThreadPool(String id, String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory, RejectedExecutionHandler handler){
        this.id = id;
        this.name = name;
        this.executor = new DynamicThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
    }
    public DynamicThreadPool(String id, String name, int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue, ThreadFactory threadFactory){
        this.id = id;
        this.name = name;
        this.executor = new DynamicThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory);
    }

    public ThreadPoolBaseParams getThreadPoolBaseParams() {
        if (threadPoolBaseParams == null){
            synchronized (this){
                if (threadPoolBaseParams != null) return threadPoolBaseParams;
                threadPoolBaseParams = new ThreadPoolBaseParams();
                threadPoolBaseParams.setMaximumPoolSize(executor.getMaximumPoolSize());
                threadPoolBaseParams.setCorePoolSize(executor.getCorePoolSize());
                threadPoolBaseParams.setPoolSize(executor.getPoolSize());
                threadPoolBaseParams.setId(id);
                threadPoolBaseParams.setName(name);
            }
        }

        return threadPoolBaseParams;
    }

    public class DynamicThreadPoolExecutor extends ThreadPoolExecutor {

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
