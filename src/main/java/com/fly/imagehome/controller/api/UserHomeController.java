package com.fly.imagehome.controller.api;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fly.imagehome.constant.BasePageConstant;
import com.fly.imagehome.pojo.UserHome;
import com.fly.imagehome.service.userhome.LoadDataService;
import com.fly.imagehome.service.userhome.UserHomeService;
import com.fly.imagehome.utils.Result;
import com.fly.imagehome.vo.userhome.requestvo.LevelCodeRequestVo;
import com.fly.imagehome.vo.userhome.requestvo.UserHomeRequestVo;
import com.fly.imagehome.vo.userhome.requestvo.UserHomeUpdateRequestVo;
import com.fly.imagehome.vo.userhome.requestvo.UserNameRequestVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @Classname UserController
 * @Description UserController
 * @Date 2021/12/6 17:22
 * @Author Fly
 * @Version 1.0
 */
@Api(value = "userhome-api", tags = {"userhome-api"})
@RestController
@RequestMapping(value = "/api/userhome")
public class UserHomeController {

    @Autowired
    private UserHomeService userHomeService;

    @Autowired
    private LoadDataService loadDataService;

    @ApiOperation("根据用户id查询")
    @GetMapping("v1/getUserById")
    public Result<UserHome> getUserById(@RequestParam("id") @NotNull(message = "id不能为空") Integer id) {
        return Result.success(userHomeService.getUserHomeById(id));
    }

    @ApiOperation("根据用户name查询")
    @PostMapping("v1/getUserHomeByName")
    public Result<IPage<UserHome>> getUserHomeByName(@RequestBody @Valid UserNameRequestVo userNameRequestVo) {
        return Result.success(userHomeService.getUserHomeByName(userNameRequestVo));
    }

    @ApiOperation("根据用户groupId查询")
    @GetMapping("v1/getUserHomeByGroupId")
    public Result<List<UserHome>> getUserHomeByGroupId(@RequestParam("groupCode") @NotNull(message = "groupCode不能为空") String groupCode) {
        //模拟一次网络延时
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("测试高并发问题");
        return Result.success(userHomeService.getUserHomeByGroupId(groupCode));
    }

    @ApiOperation("根据用户levelCode查询")
    @PostMapping("v1/getUserHomeByLevelCode")
    public Result<IPage<UserHome>> getUserHomeByLevelCode(@RequestBody @Valid LevelCodeRequestVo levelCodeRequestVo) {
        return Result.success(userHomeService.getUserHomeByLevelCode(levelCodeRequestVo));
    }

    @ApiOperation("添加userhome")
    @PostMapping("v1/createUserHome")
    public Result<UserHome> createUserHome(@RequestBody UserHomeRequestVo userHomeRequestVo) {
        return Result.success(userHomeService.createUserHome(userHomeRequestVo));
    }

    @ApiOperation("更新userhome")
    @PostMapping("v1/updateUserHome")
    public Result<UserHome> updateUserHome(@RequestBody @Valid UserHomeUpdateRequestVo userHomeUpdateRequestVo) {
        return Result.success(userHomeService.updateUserHome(userHomeUpdateRequestVo));
    }

    @ApiOperation("自动生数据写入文件中")
    @GetMapping("v1/generateData")
    public Result<Void> generateDate(@RequestParam("number") @NotNull(message = "生成数据数目不能为空") Long number) {
        loadDataService.generateDate(number);
        return Result.success();
    }

    @ApiOperation("文本数据导入数据库")
    @GetMapping("v1/importMysql")
    public Result<Void> importMysql() {
        loadDataService.importMysql();
        return Result.success();
    }

    @ApiOperation("分页查询所有数据")
    @PostMapping("v1/findList")
    public Result<IPage<UserHome>> findList(@RequestBody BasePageConstant basePageConstant) {
        return Result.success(userHomeService.findList(basePageConstant));
    }

    @ApiOperation("多线程删除所有数据")
    @PostMapping("v1/deleteAllList")
    public Result<Void> deleteAllList() {
        userHomeService.deleteAllList();
        return Result.success();
    }
}


