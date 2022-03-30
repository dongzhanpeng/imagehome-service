package com.fly.imagehome.constant.basismapenum;

import lombok.Getter;

/**
 * @Classname LevelEnum
 * @Description LevelEnum
 * @Date 2021/12/28 11:11
 * @Author Fly
 * @Version 1.0
 */
@Getter
public enum LevelEnum {

    NATIONWIDE(0, "全国"),
    PROVINCE(1, "省"),
    CITY(2, "市"),
    AREA(3, "区"),
    TOWN(4, "乡/镇/街道");


    private Integer code;
    private String name;

    LevelEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static String getNameByCode(Integer code) {
        for (LevelEnum c : LevelEnum.values()) {
            if (c.getCode().equals(code)) {
                return c.name;
            }
        }
        return null;
    }
}
