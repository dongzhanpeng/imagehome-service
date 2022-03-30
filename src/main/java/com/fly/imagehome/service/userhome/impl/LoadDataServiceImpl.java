package com.fly.imagehome.service.userhome.impl;

import com.fly.imagehome.service.userhome.LoadDataService;
import com.fly.imagehome.utils.UuidUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @Classname LoadDataServiceImpl
 * @Description LoadDataService
 * @Date 2021/12/15 17:11
 * @Author Fly
 * @Version 1.0
 */
@Service
@Slf4j
public class LoadDataServiceImpl implements LoadDataService {

    @Value("${spring.datasource.driverClassName}")
    private String driverClassName;

    @Value("${spring.datasource.url}")
    private String url;


    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Override
    public void generateDate(Long number) {
        try {
            FileWriter fileWriter = new FileWriter("D:/Users/dongzhanpeng/Desktop/generateData.txt");
            long start = System.currentTimeMillis();
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            String str = "'";
            Integer id;
            String uuid;
            String user_name;
            String group_code;
            String group_name;
            String level_code;
            String level_name;
            String creat_code;
            String creat_name;

            for (int i = 1; i <= number; i++) {
                id = i;
                uuid = UuidUtil.getUUID();
                user_name = "dongzhanpeng" + "_" + i;
                group_code = "Home";
                group_name = "HOME" + "_" + i;
                level_code = "LE" + "_" + i;
                level_name = "SUPER" + "_" + i;
                creat_code = "dongzhanpeng";
                creat_name = "董占鹏";
                bufferedWriter.write(str + id + str + ","
                        + str + uuid + str + ","
                        + str + user_name + str + ","
                        + str + group_code + str + ","
                        + str + group_name + str + ","
                        + str + level_code + str + ","
                        + str + level_name + str + ","
                        + str + creat_code + str + ","
                        + str + creat_name + str + "\n");
            }
            bufferedWriter.close();
            fileWriter.close();
            log.info("执行完毕");
            long end = System.currentTimeMillis();
            log.info("insert data：time：{}", (end - start + "ms"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importMysql() {

        final String sql =
                "LOAD DATA LOCAL INFILE 'D:/Users/dongzhanpeng/Desktop/generateData.txt'"
                        + " REPLACE INTO TABLE imagehome_userhome FIELDS TERMINATED BY ',' ENCLOSED BY '\\''"
                        + " LINES TERMINATED BY '\\n' (id,uuid,user_name,group_code,group_name,level_code,level_name,creat_code,creat_name)";
        try {
            Class.forName(driverClassName);
            Connection conn = DriverManager.getConnection(url, username, password);
            long start = System.currentTimeMillis();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.execute();
            long end = System.currentTimeMillis();
            log.info("load data time：{}" + (end - start) + "ms");
            pstmt.close();
            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
