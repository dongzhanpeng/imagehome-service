package com.fly.imagehome.pojo;

import com.fly.imagehome.utils.UuidUtil;
import lombok.Data;

/**
 * @Classname BaseEntiy
 * @Description BaseEntiy
 * @Date 2021/12/8 9:58
 * @Author Fly
 * @Version 1.0
 */
@Data
public class BaseEntiy {

    public static final String PK_FILED = "id";

    // 主键
    private Long id;

    private String uuid;

    private String creatCode;

    private String creatName;

    public void createInit() {
        this.setUuid(UuidUtil.getUUID());
        this.setCreatCode("dongzhanpeng");
        this.setCreatName("董占鹏");
    }
}
