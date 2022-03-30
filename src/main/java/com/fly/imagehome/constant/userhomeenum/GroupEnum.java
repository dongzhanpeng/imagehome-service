package com.fly.imagehome.constant.userhomeenum;

import com.fly.imagehome.constant.commonenum.DeleteFlagEnum;

/**
 * @Classname GroupEnum
 * @Description GroupEnum
 * @Date 2021/12/8 10:30
 * @Author Fly
 * @Version 1.0
 */
public enum GroupEnum {

    DELETE_FLAG_YES("YES", ""),
    DELETE_FLAG_NO("NO", "");

    private String code;
    private String name;

    private GroupEnum(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String getCode() {
        return code;
    }

    public static String getNameByCode(Integer code) {
        for (GroupEnum c : GroupEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.name;
            }
        }
        return null;
    }
}
