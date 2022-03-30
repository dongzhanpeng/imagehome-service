package com.fly.imagehome.vo.basismap.requestvo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Classname RegionRequestVo
 * @Description RegionRequestVo
 * @Date 2021/12/28 10:59
 * @Author Fly
 * @Version 1.0
 */
@Data
public class RegionRequestVo implements Serializable {

    @ApiModelProperty("检索行政区划关键字")
    @NotNull(message = "行政区关键字不能为空")
    private String keyword;

    @ApiModelProperty(value = "行政区划显示子级级数,可填入：0、1、2、3等数字(默认0)", example = "0")
    private String subAdmin;
}
