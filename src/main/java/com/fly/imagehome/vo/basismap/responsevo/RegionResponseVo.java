package com.fly.imagehome.vo.basismap.responsevo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname RegionResponseVo
 * @Description RegionResponseVo
 * @Date 2021/12/28 10:59
 * @Author Fly
 * @Version 1.0
 */
@Data
public class RegionResponseVo implements Serializable {

    private String message;

    @ApiModelProperty("行政区划数据版本")
    private String data_version;

    @ApiModelProperty("行政区划个数")
    private Integer result_size;

    @ApiModelProperty("返回状态，0为成功")
    private Integer status;

    private List<RegionDistricts> districts;

    public boolean isSuccess() {
        return this.status == 0;
    }
}
