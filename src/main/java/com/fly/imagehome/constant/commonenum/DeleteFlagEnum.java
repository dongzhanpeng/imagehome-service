package com.fly.imagehome.constant.commonenum;

import lombok.Getter;

/**
 * @Classname DeleteFlagEnum
 * @Description DeleteFlagEnum
 * @Date 2021/12/8 10:05
 * @Author Fly
 * @Version 1.0
 */
@Getter
public enum DeleteFlagEnum {

    DELETE_FLAG_YES("YES", 1),
    DELETE_FLAG_NO("NO", 0);

    private Integer code;
    private String name;

    DeleteFlagEnum(String name, Integer code) {
        this.name = name;
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Integer getCode() {
        return code;
    }

    public static String getNameByCode(Integer code) {
        for (DeleteFlagEnum c : DeleteFlagEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.name;
            }
        }
        return null;
    }
}
