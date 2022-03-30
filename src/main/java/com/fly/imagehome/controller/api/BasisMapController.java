package com.fly.imagehome.controller.api;

import com.alibaba.fastjson.JSON;
import com.fly.imagehome.exception.BizException;
import com.fly.imagehome.service.userhome.BasisMapService;
import com.fly.imagehome.utils.Result;
import com.fly.imagehome.vo.basismap.requestvo.RegionRequestVo;
import com.fly.imagehome.vo.basismap.responsevo.RegionDistricts;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Classname BasisMapController
 * @Description 百度地图服务接口
 * @Date 2021/12/28 10:50
 * @Author Fly
 * @Version 1.0
 */
@Api(value = "basismap-api", tags = {"basismap-api"})
@RestController
@RequestMapping(value = "api/basismap")
@Slf4j
public class BasisMapController {

    @Autowired
    private BasisMapService basisMapService;

    @ApiOperation(value = "行政区划查询")
    @PostMapping("/v1/region/search")
    public Result<List<RegionDistricts>> regionSearch(@RequestBody RegionRequestVo regionRequestVo) {
        List<RegionDistricts> result = null;
        int count = 0;
        Exception exception = null;
        //设置重试机制
        while (true) {
            count++;
            try {
                result = basisMapService.regionSearch(regionRequestVo);
                break;
            } catch (Exception e) {
                if (e.getMessage().contains("APP不存在，AK有误请检查再重试")) {
                    if (count > 3) {
                        exception = e;
                        log.info("map search retry mechanism：retry : {}", JSON.toJSONString(count));
                        break;
                    }
                } else {
                    exception = e;
                    break;
                }
            }
        }
        if (exception != null) {
            throw new BizException(exception.getMessage());
        }
        return Result.success(result);
    }
}
