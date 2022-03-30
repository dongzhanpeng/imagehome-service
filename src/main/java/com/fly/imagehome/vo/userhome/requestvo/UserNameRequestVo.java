package com.fly.imagehome.vo.userhome.requestvo;

import com.fly.imagehome.constant.BasePageConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @Classname UserNameRequestVo
 * @Description UserNameRequestVo
 * @Date 2021/12/7 16:04
 * @Author Fly
 * @Version 1.0
 */
@Data
public class UserNameRequestVo extends BasePageConstant implements Serializable {

    @ApiModelProperty("用户名称列表")
    @NotEmpty(message="『用户名称』未填写，请填写后保存。")
    private List<String> userNames;
}
