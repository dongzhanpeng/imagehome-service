package com.fly.imagehome.exception;

/**
 * @Classname BaseErrorInfo
 * @Description TODO
 * @Date 2021/12/8 13:51
 * @Author Fly
 * @Version 1.0
 */
public interface BaseErrorInfo {
    /** 错误码 */
    String getResultCode();

    /** 错误描述 */
    String getResultMsg();
}
