package com.fly.imagehome.service.userhome.impl;

import com.alibaba.fastjson.JSON;
import com.fly.imagehome.exception.BizException;
import com.fly.imagehome.feign.BaiduMapFeign;
import com.fly.imagehome.service.userhome.BasisMapService;
import com.fly.imagehome.vo.basismap.requestvo.RegionRequestVo;
import com.fly.imagehome.vo.basismap.responsevo.RegionDistricts;
import com.fly.imagehome.vo.basismap.responsevo.RegionResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Classname BasisMapServiceImpl
 * @Description BasisMapServiceImpl
 * @Date 2021/12/28 13:47
 * @Author Fly
 * @Version 1.0
 */
@Service
@Slf4j
public class BasisMapServiceImpl implements BasisMapService {

    @Autowired
    private BaiduMapFeign baiduMapFeign;

    @Value("${imagehome.basismap.ak}")
    private String ak;

    @Override
    public List<RegionDistricts> regionSearch(RegionRequestVo regionRequestVo) {
        String resultStr = baiduMapFeign.regionSearch(ak, regionRequestVo.getKeyword(), regionRequestVo.getSubAdmin(), "1");
        log.info("baidu region search result：{}", resultStr);
        RegionResponseVo result = JSON.parseObject(resultStr, RegionResponseVo.class);
        if (!result.isSuccess()) {
            throw new BizException("调用百度地图行政区查询服务错误", result.getMessage());
        }
        return result.getDistricts();
    }
}
