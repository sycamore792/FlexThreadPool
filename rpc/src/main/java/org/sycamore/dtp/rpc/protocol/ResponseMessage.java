package org.sycamore.dtp.rpc.protocol;

import lombok.Data;

/**
 * @CLASS_NAME: ResponseMessage
 * @DESCRIPTION: RPC响应体
 * @CREATER: 桑运昌
 * @DATE: 2024/1/16 15:11
 */
@Data
public class ResponseMessage {
    /**
     * 响应对应的请求ID
     */
    private String requestId;
    /**
     * 调用是否成功的标识
     */
    private boolean success = true;
    /**
     * 调用错误信息
     */
    private String errorMessage;
    /**
     * 调用结果
     */
    private Object result;
}
