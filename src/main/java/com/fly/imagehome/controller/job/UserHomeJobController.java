package com.fly.imagehome.controller.job;

import com.fly.imagehome.service.userhome.UserHomeService;
import com.fly.imagehome.utils.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname UserHomeJobController
 * @Description userhome定时任务
 * @Date 2021/12/9 14:01
 * @Author Fly
 * @Version 1.0
 */
@Api(value = "userhome-job", tags = {"userhome-job"})
@RestController
@RequestMapping(value = "/job/imagehome")
public class UserHomeJobController {

    @Autowired
    private UserHomeService userHomeService;

    @Scheduled(cron = "0 0 0-6 * * ? ")
    @ApiOperation("定时任务获取所有用户name")
    @PostMapping("v1/getAllUser")
    public Result<List<String>> getAllUser() {
        return Result.success(userHomeService.getAllUser());
    }
}
