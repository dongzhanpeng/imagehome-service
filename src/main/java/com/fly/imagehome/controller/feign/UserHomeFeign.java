package com.fly.imagehome.controller.feign;

import com.fly.imagehome.pojo.UserHome;
import com.fly.imagehome.service.userhome.UserHomeService;
import com.fly.imagehome.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @Classname UserHomeFeign
 * @Description UserHomeFeign
 * @Date 2021/12/15 10:43
 * @Author Fly
 * @Version 1.0
 */
@Api(value = "userhome-feign", tags = {"userhome-feign"})
@RestController
@RequestMapping(value = "/feign/imagehome")
public class UserHomeFeign {

    @Autowired
    private UserHomeService userHomeService;

    @ApiOperation("根据用户uuid查询")
    @GetMapping("v1/getUserHomeByUuid")
    public Result<UserHome> getUserHomeByUuid(@RequestParam("uuid") @NotNull(message = "uuid不能为空") String uuid) {
        return Result.success(userHomeService.getUserHomeByUuid(uuid));
    }
}
