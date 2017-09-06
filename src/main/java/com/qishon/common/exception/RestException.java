package com.qishon.common.exception;


/**
 * @author kexia.lu on 2017/8/11.
 */
public class RestException extends RuntimeException {

    private int status; //手动将500code转为该http code

    private String debugMessage; //debug模式下，log中输出的详细错误信息

    private int code; //500code下 细化的自定义错误码

    public RestException() {
    }

    /**
     *
     * @param status http状态码
     * @param message 错误信息
     */
    public RestException(int status, String message) {
        super(message);
        this.status = status;
        this.debugMessage = message;
    }

    /**
     *
     * @param status http状态码
     * @param message 错误信息
     * @param debugMessage 详细错误信息
     */
    public RestException(int status, String message, String debugMessage) {
        super(message);
        this.status = status;
        this.debugMessage = debugMessage;
    }

    /**
     *
     * @param status http状态码
     * @param code 自定义详细错误码
     * @param message 错误信息
     */
    public RestException(int status, int code, String message) {
        super(message);
        this.status = status;
        this.code = code;
        this.debugMessage = message;
    }

    /**
     *
     * @param status http状态码
     * @param code 自定义详细错误码
     * @param message 错误信息
     * @param debugMessage 详细错误信息
     */
    public RestException(int status, int code, String message, String debugMessage) {
        super(message);
        this.status = status;
        this.code = code;
        this.debugMessage = debugMessage;
    }

    public int getStatus() {
        return status;
    }

    public String getDebugMessage() {
        return debugMessage;
    }

    public int getCode() {
        return code;
    }
}
