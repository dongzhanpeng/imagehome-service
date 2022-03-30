package com.fly.imagehome.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * @Classname BaiduMapFeign
 * @Description BaiduMapFeign
 * @Date 2021/12/28 10:59
 * @Author Fly
 * @Version 1.0
 */
@FeignClient(value = "yingyan.baidu.com", url = "http://api.map.baidu.com")
public interface BaiduMapFeign {

    @RequestMapping(path = "/api_region_search/v1/", method = RequestMethod.GET, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String regionSearch(@RequestParam("ak") String ak, @RequestParam("keyword") String keyword, @RequestParam("sub_admin") String sub_admin, @RequestParam("extensions_code") String extensions_code);
}
