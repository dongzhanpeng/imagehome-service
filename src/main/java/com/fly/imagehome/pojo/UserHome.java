package com.fly.imagehome.pojo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Classname UserHome
 * @Description UserHome
 * @Date 2021/12/6 17:16
 * @Author Fly
 * @Version 1.0
 */
@Data
@TableName("imagehome_userhome")
public class UserHome extends BaseEntiy{

    private String userName;

    private String levelCode;

    private String levelName;

    @TableField(value = "group_code")
    private String groupCode;

    private String groupName;

}
