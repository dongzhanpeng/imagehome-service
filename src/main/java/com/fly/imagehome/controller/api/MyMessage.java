package com.fly.imagehome.controller.api;

import com.fly.imagehome.utils.UuidUtil;
import lombok.Data;

/**
 * @Classname MyMessage
 * @Description TODO
 * @Date 2022/1/7 11:13
 * @Author Fly
 * @Version 1.0
 */
@Data
public class MyMessage {

    private String userId;

    private String message;

    public MyMessage() {
        String userId = UuidUtil.getUUID();
        this.userId = userId;
    }
}
