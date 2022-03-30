package com.fly.imagehome;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableAsync
@SpringBootApplication
@MapperScan("com.fly.imagehome.mapper")
@EnableCaching
@EnableScheduling
@EnableDiscoveryClient
@RefreshScope
@EnableFeignClients(basePackages = {"com.fly"})
public class ImagehomeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImagehomeServiceApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  ImageHome项目启动成功   ლ(´ڡ`ლ)ﾞ ");
    }

}
