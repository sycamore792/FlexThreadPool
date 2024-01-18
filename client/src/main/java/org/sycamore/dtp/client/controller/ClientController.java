package org.sycamore.dtp.client.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.sycamore.dtp.client.remote.RpcService;
import org.sycamore.dtp.common.ThreadPoolChangeableParams;
import org.sycamore.dtp.common.database.ThreadPoolDO;
import org.sycamore.dtp.common.web.base.Result;
import org.sycamore.dtp.common.web.base.Results;
import org.sycamore.dtp.common.web.exception.ServiceException;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @CLASS_NAME: ClientController
 * @DESCRIPTION: 客户端前端控制器
 * @CREATER: 桑运昌
 * @DATE: 2024/1/11 11:59
 */
@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientController {


    private final RpcService rpcService;

    @GetMapping("health-check")
    public Result healthCheck() {
        if (rpcService.healthCheck()) return Results.success("健康检查成功");
        return Results.failure(new ServiceException("服务异常"));
    }


    @GetMapping("pool-list/{pageName}/{pageSize}")
    public Result getPoolList(@PathVariable("pageName") String pageName, @PathVariable("pageSize") Integer pageSize) {
        return null;
    }

    @PostMapping("/update-pool-params/{id}")
    public Result updatePoolParams(@PathVariable("id") String id, @RequestBody ThreadPoolChangeableParams threadPoolChangeableParams) {
        return null;
    }



//    @GetMapping
//    public String getThreadPoolConfig() {
//        // 获取 ID 和 名称
//        String id = testThreadPool.getId();
//        String name = testThreadPool.getName();
//        ThreadPoolExecutor threadPoolExecutor = testThreadPool.getThreadPoolExecutor();
//        boolean allowsCoreThreadTimeOut = threadPoolExecutor.allowsCoreThreadTimeOut();//设置是否回收在保活时间后依然没没有任务执行核心线程
//        // 获取 ThreadPoolExecutor 的各种参数
//        int corePoolSize = threadPoolExecutor.getCorePoolSize(); // 核心线程数
//        int maximumPoolSize = threadPoolExecutor.getMaximumPoolSize(); // 最大线程数
//        long keepAliveTime = threadPoolExecutor.getKeepAliveTime(TimeUnit.MILLISECONDS); // 线程保持活动时间（毫秒）
//        int poolSize = threadPoolExecutor.getPoolSize(); // 线程池当前大小
//        int activeCount = threadPoolExecutor.getActiveCount(); // 活动线程数
//        long completedTaskCount = threadPoolExecutor.getCompletedTaskCount(); // 已完成任务数
//        long taskCount = threadPoolExecutor.getTaskCount(); // 任务总数
//        boolean isShutdown = threadPoolExecutor.isShutdown(); // 线程池是否已关闭
//        boolean isTerminated = threadPoolExecutor.isTerminated(); // 线程池是否已终止
//        // 获取线程池队列信息
//        BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue(); // 线程池的任务队列
//        int queueSize = queue.size(); // 队列中的任务数
//        int remainingCapacity = queue.remainingCapacity(); // 队列剩余容量
//        ThreadPoolDO threadPoolDO = new ThreadPoolDO();
//        threadPoolDO.setId(Long.valueOf(id));
//        threadPoolDO
//                .setThreadPoolName(name)
//                .setCorePoolSize(corePoolSize)
//                .setMaximumPoolSize(maximumPoolSize)
//                .setKeepAliveTimes(keepAliveTime)
//                .setPoolSize(poolSize)
//        ;
//        testThreadPool.getThreadPoolExecutor().execute(() ->{
//            try {
//                Thread.sleep(4000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            }
//        });
//        // 拼接并返回所有信息
//        return "ID: " + id + "<br>"+
//                "名称: " + name + "<br>"+
//                "核心线程数: " + corePoolSize + "<br>"+
//                "最大线程数: " + maximumPoolSize + "<br>"+
//                "线程保持活动时间: " + keepAliveTime + "毫秒" + "<br>"+
//                "线程池当前大小: " + poolSize + "<br>"+
//                "活动线程数: " + activeCount + "<br>"+
//                "已完成任务数: " + completedTaskCount + "<br>"+
//                "任务总数: " + taskCount + "<br>"+
//                "线程池是否已关闭: " + isShutdown + "<br>"+
//                "线程池是否已终止: " + isTerminated+"<br>"+
//                "队列中的任务数: " + queueSize + "<br>"+
//                "队列剩余容量: " + remainingCapacity;
//    }

    @PutMapping
    public String updateThreadPoolConfig(){
        return "Thread pool updated successfully";
    }
}
