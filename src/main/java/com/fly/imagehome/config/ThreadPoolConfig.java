
package com.fly.imagehome.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Classname ThreadPoolConfig
 * @Description 线程池配置类
 * @Date 2021/12/6 14:58
 * @Author Fly
 * @Version 1.0
 */
@Configuration
@Slf4j
public class ThreadPoolConfig {

    @Bean
    public ThreadPoolExecutor threadPoolExecutor() {
        @SuppressWarnings("static-access")
        ThreadFactory factory = (Runnable r) -> {
            // 创建一个线程
            Thread t = new Thread(r);
            // 给创建的线程设置UncaughtExceptionHandler对象 里面实现异常的默认逻辑
            t.setDefaultUncaughtExceptionHandler((Thread thread, Throwable e) -> {
                log.error(thread.getName() + e.getMessage(), e);
            });
            return t;
        };
        return new ThreadPoolExecutor(5, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(10000), factory,
                new ThreadPoolExecutor.AbortPolicy());
    }
}
