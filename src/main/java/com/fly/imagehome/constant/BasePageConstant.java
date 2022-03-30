package com.fly.imagehome.constant;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Classname BasePageConstant
 * @Description BasePageConstant
 * @Date 2021/12/7 16:53
 * @Author Fly
 * @Version 1.0
 */
@Data
public class BasePageConstant implements Serializable {

    @ApiModelProperty(value = "当前页", example = "1")
    private int pageNumber = 1;

    @ApiModelProperty(value = "每页显示数", example = "10")
    private int pageSize = 10;
}
