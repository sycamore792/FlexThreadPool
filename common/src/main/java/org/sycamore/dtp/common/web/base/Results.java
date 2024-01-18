package org.sycamore.dtp.common.web.base;


import org.sycamore.dtp.common.web.exception.ErrorCode;
import org.sycamore.dtp.common.web.exception.ServiceException;

/**
 * Result 工具类
 */
public final class Results {

    public static Result<Void> success() {
        return new Result<Void>()
                .setCode(Result.SUCCESS_CODE);
    }

    public static <T> Result<T> success(T data) {
        return new Result<T>()
                .setCode(Result.SUCCESS_CODE)
                .setData(data);
    }

    public static <T> Result<T> failure(ServiceException serviceException) {
        return new Result<T>().setCode(ErrorCode.SERVICE_ERROR.getCode())
                .setMessage(serviceException.getMessage());
    }

    public static <T> Result<T> failure(String code, String message) {
        return new Result<T>()
                .setCode(code)
                .setMessage(message);
    }

}
