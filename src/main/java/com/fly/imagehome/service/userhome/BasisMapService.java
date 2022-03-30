package com.fly.imagehome.service.userhome;

import com.fly.imagehome.vo.basismap.requestvo.RegionRequestVo;
import com.fly.imagehome.vo.basismap.responsevo.RegionDistricts;

import java.util.List;

/**
 * @Classname BasisMapService
 * @Description BasisMapService
 * @Date 2021/12/28 13:46
 * @Author Fly
 * @Version 1.0
 */
public interface BasisMapService {

    List<RegionDistricts> regionSearch(RegionRequestVo regionRequestVo);
}
