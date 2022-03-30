package com.fly.imagehome.service.userhome;

import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Classname LoadDataService
 * @Description LoadDataService
 * @Date 2021/12/15 17:10
 * @Author Fly
 * @Version 1.0
 */
public interface LoadDataService {
    void generateDate(@RequestParam("number") Long number);

    void importMysql();
}
