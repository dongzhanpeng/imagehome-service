package com.fly.imagehome.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Classname WebSecurityConfg
 * @Description 安全框架配置
 * @Date 2022/1/7 15:22
 * @Author Fly
 * @Version 1.0
 */
@Configuration
@EnableWebSecurity //开启Spring Security的功能
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfg extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /**
         * 基于内存的方式，创建两个用户admin/123456，user/123456
         * */
        auth.inMemoryAuthentication()
                .withUser("admin1")//用户名
                .password(passwordEncoder().encode("admin1"))//密码
                .roles("ADMIN");//角色
        auth.inMemoryAuthentication()
                .withUser("user1")//用户名
                .password(passwordEncoder().encode("user1"))//密码
                .roles("USER");//角色
        auth.inMemoryAuthentication()
                .withUser("董占鹏")//用户名
                .password(passwordEncoder().encode("dzp"))//密码
                .roles("USER");//角色
        auth.inMemoryAuthentication()
                .withUser("郭帅")//用户名
                .password(passwordEncoder().encode("gs"))//密码
                .roles("USER");//角色
    }

    /**
     * 指定加密方式
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        // 使用BCrypt加密密码
        return new BCryptPasswordEncoder();
    }
}
