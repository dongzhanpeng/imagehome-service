package com.fly.imagehome.vo.userhome.requestvo;

import com.fly.imagehome.constant.BasePageConstant;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

/**
 * @Classname LevelCodeRequestVo
 * @Description LevelCodeRequestVo
 * @Date 2021/12/7 16:19
 * @Author Fly
 * @Version 1.0
 */
@Data
public class LevelCodeRequestVo extends BasePageConstant implements Serializable {

    @ApiModelProperty("levelCodes列表")
    @NotEmpty(message = "『levelCodes』未填写，请填写后保存。")
    private List<String> levelCodes;
}
