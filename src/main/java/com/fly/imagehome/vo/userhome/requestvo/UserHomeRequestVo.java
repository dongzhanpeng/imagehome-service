package com.fly.imagehome.vo.userhome.requestvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname UserHomeRequestVo
 * @Description UserHomeRequestVo
 * @Date 2021/12/8 10:39
 * @Author Fly
 * @Version 1.0
 */
@Data
public class UserHomeRequestVo {

    @ApiModelProperty("userName")
    private String userName;

    @ApiModelProperty("星级code")
    private String levelCode;

    @ApiModelProperty("星级name")
    private String levelName;

    @ApiModelProperty("分组code")
    @NotNull(message = "分组code不能为空")
    private String groupCode;

    @ApiModelProperty("分组name")
    private String groupName;
}
