package com.fly.imagehome.vo.userhome.requestvo;

import com.fly.imagehome.aop.BaseLockKey;
import com.fly.imagehome.aop.annotation.LockGroupCodeKey;
import com.fly.imagehome.aop.annotation.LockKey;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Classname UserHomeUpdateRequestVo
 * @Description UserHomeUpdateRequestVo
 * @Date 2021/12/9 14:26
 * @Author Fly
 * @Version 1.0
 */
@Data
public class UserHomeUpdateRequestVo extends BaseLockKey {

    @ApiModelProperty("用户Uuid")
    @NotNull(message = "用户uuid不能为空")
    @LockKey
    private String uuid;

    @ApiModelProperty("userName")
    private String userName;

    @ApiModelProperty("星级code")
    private String levelCode;

    @ApiModelProperty("星级name")
    private String levelName;

    @ApiModelProperty("分组code")
    @NotNull(message = "分组code不能为空")
    @LockGroupCodeKey
    private String groupCode;

    @ApiModelProperty("分组name")
    private String groupName;
}
