package com.fly.imagehome.service.userhome;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fly.imagehome.constant.BasePageConstant;
import com.fly.imagehome.pojo.UserHome;
import com.fly.imagehome.vo.userhome.requestvo.LevelCodeRequestVo;
import com.fly.imagehome.vo.userhome.requestvo.UserHomeRequestVo;
import com.fly.imagehome.vo.userhome.requestvo.UserHomeUpdateRequestVo;
import com.fly.imagehome.vo.userhome.requestvo.UserNameRequestVo;

import javax.websocket.server.PathParam;
import java.util.List;

/**
 * @Classname UserHomeService
 * @Description UserHomeService
 * @Date 2021/12/7 14:20
 * @Author Fly
 * @Version 1.0
 */
public interface UserHomeService {

    UserHome getUserHomeById(@PathParam("id") Integer id);

    UserHome getUserHomeByUuid(@PathParam("uuid") String uuid);

    IPage<UserHome> getUserHomeByName(UserNameRequestVo userNameRequestVo);

    List<UserHome> getUserHomeByGroupId(@PathParam("groupId") String groupId);

    IPage<UserHome> getUserHomeByLevelCode(LevelCodeRequestVo levelCodeRequestVo);

    UserHome createUserHome(UserHomeRequestVo userHomeRequestVo);

    List<String> getAllUser();

    UserHome updateUserHome(UserHomeUpdateRequestVo userHomeUpdateRequestVo);

    IPage<UserHome> findList(BasePageConstant basePageConstant);

    void deleteAllList();
}
