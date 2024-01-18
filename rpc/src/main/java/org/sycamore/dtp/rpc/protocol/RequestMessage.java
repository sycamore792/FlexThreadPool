package org.sycamore.dtp.rpc.protocol;

import lombok.Data;

/**
 * @CLASS_NAME: RequestMessage
 * @DESCRIPTION: RPC请求体
 * @CREATER: 桑运昌
 * @DATE: 2024/1/16 15:10
 */
@Data
public class RequestMessage {
    /**
     * 请求ID 用来标识本次请求以匹配RPC服务器的响应
     */
    private String requestId;
    /**
     * 调用的类(接口)权限定名称
     */
    private String className;
    /**
     * 调用的方法名
     */
    private String methodName;
    /**
     * 方法参类型列表
     */
    private Class<?>[] parameterTypes;
    /**
     * 方法参数
     */
    private Object[] parameters;
}
