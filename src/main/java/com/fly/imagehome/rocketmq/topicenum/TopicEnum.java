package com.fly.imagehome.rocketmq.topicenum;

/**
 * @Classname TopicEnum
 * @Description TopicEnum
 * @Date 2021/12/13 14:40
 * @Author Fly
 * @Version 1.0
 */
public enum TopicEnum {

    IMAGE_HOME_TEST_TOPIC("image_home_test_topic","测试topic");

    private String name;

    private String code;

    private TopicEnum(String code, String name) {
        this.name = name;
        this.code = code;
    }


    public String getName() {
        return name;
    }


    public String getCode() {
        return code;
    }


    public static String getNameByCode(String code) {
        for (TopicEnum c : TopicEnum.values()) {
            if (c.code.equals(code)) {
                return c.name;
            }
        }
        return null;
    }

}
