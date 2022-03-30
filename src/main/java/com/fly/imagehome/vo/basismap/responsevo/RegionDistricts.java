package com.fly.imagehome.vo.basismap.responsevo;

import com.fly.imagehome.constant.basismapenum.LevelEnum;
import com.fly.imagehome.constant.inter.SwaggerApiEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname RegionDistricts
 * @Description RegionDistricts
 * @Date 2021/12/28 11:01
 * @Author Fly
 * @Version 1.0
 */
@Data
@ApiModel
public class RegionDistricts implements Serializable {

    @ApiModelProperty("行政区划编码")
    private String code;

    @ApiModelProperty("行政区划名称")
    private String name;

    @ApiModelProperty("行政区划级别")
    @SwaggerApiEnum(type = LevelEnum.class, c = "code", v = "name")
    private String level;

    @ApiModelProperty("下级行政区列表")
    private List<RegionDistricts> districts;
}
