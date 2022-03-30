package com.fly.imagehome.utils;

/**
 * @Classname Result
 * @Description 统一结果处理
 * @Date 2021/12/7 14:10
 * @Author Fly
 * @Version 1.0
 */

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1686054524991742104L;
    protected Integer code = 200;
    protected String msg = "success";
    protected T data;
    private Map<String, String> enumVal = new HashMap();

    public Result() {
    }

    public Result(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static <T> Result<T> error(String msg) {
        return new Result(-1, msg);
    }

    public static <T> Result<T> error(String msg, T data) {
        Result<T> result = new Result(-1, msg);
        if (null != data) {
            result.setData(data);
        }

        return result;
    }

    public static <T> Result<T> error(Integer code, String msg, T data) {
        Result<T> result = new Result(code, msg);
        if (null != data) {
            result.setData(data);
        }

        return result;
    }

    public static <T> Result<T> success() {
        return (Result<T>) success((String)null, (Object)null);
    }

    public static <T> Result<T> success(T data) {
        return success((String)null, data);
    }

    public static <T> Result<T> success(String msg, T data) {
        Result<T> result = new Result();
        if (StringUtils.isNotBlank(msg)) {
            result.setMsg(msg);
            result.setCode(200);
        }

        if (null != data) {
            result.setData(data);
        }

        return result;
    }

    public boolean isSuccess() {
        return this.code == 200;
    }

    public void putEnumVal(String key, String valse) {
        this.enumVal.put(key, valse);
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }

    public T getData() {
        return this.data;
    }

    public Map<String, String> getEnumVal() {
        return this.enumVal;
    }

    public void setCode(final Integer code) {
        this.code = code;
    }

    public void setMsg(final String msg) {
        this.msg = msg;
    }

    public void setData(final T data) {
        this.data = data;
    }

    public void setEnumVal(final Map<String, String> enumVal) {
        this.enumVal = enumVal;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof Result)) {
            return false;
        } else {
            Result<?> other = (Result)o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Object this$code = this.getCode();
                    Object other$code = other.getCode();
                    if (this$code == null) {
                        if (other$code == null) {
                            break label59;
                        }
                    } else if (this$code.equals(other$code)) {
                        break label59;
                    }

                    return false;
                }

                Object this$msg = this.getMsg();
                Object other$msg = other.getMsg();
                if (this$msg == null) {
                    if (other$msg != null) {
                        return false;
                    }
                } else if (!this$msg.equals(other$msg)) {
                    return false;
                }

                Object this$data = this.getData();
                Object other$data = other.getData();
                if (this$data == null) {
                    if (other$data != null) {
                        return false;
                    }
                } else if (!this$data.equals(other$data)) {
                    return false;
                }

                Object this$enumVal = this.getEnumVal();
                Object other$enumVal = other.getEnumVal();
                if (this$enumVal == null) {
                    if (other$enumVal != null) {
                        return false;
                    }
                } else if (!this$enumVal.equals(other$enumVal)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Result;
    }

    public String toString() {
        return "Result(code=" + this.getCode() + ", msg=" + this.getMsg() + ", data=" + this.getData() + ", enumVal=" + this.getEnumVal() + ")";
    }
}
