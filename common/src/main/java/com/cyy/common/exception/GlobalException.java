package com.cyy.common.exception;


import com.cyy.common.enums.ErrorCode;

/**
 * 全局通用异常类，实在不知道该抛出什么异常时可抛出该异常，尽量抛出模块对应的异常
 * @author winter
 * @author CYY
 */

public class GlobalException extends RuntimeException{
    /**
     * 错误码 default:  ErrorCode.SYSTEM_ERROR
     */
    private String code = ErrorCode.SYSTEM_ERROR.getCode();

    /**
     * 错误提示
     */
    private String message;

    public GlobalException(String message)
    {
        this.message = message;
    }

    public GlobalException(ErrorCode errorCode)
    {
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public GlobalException(ErrorCode errorCode, String message)
    {
        this.code = errorCode.getCode();
        this.message = message;
    }


    public GlobalException(String message, String code)
    {
        this.message = message;
        this.code = code;
    }

    public String getMessage()
    {
        return message;
    }

    public String getCode()
    {
        return code;
    }

    public GlobalException setMessage(String message)
    {
        this.message = message;
        return this;
    }

}
