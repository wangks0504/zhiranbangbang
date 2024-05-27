package org.zhiran.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhiran.mapper.UserMapper;
import org.zhiran.pojo.User;
import org.zhiran.utils.Md5Util;
import org.zhiran.utils.ThreadLocalUtil;

import java.util.Map;

@Service
public class UserServiceImpl extends User implements org.zhiran.service.UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUsername(String username) {
      User u = userMapper.findByUsername(username);//获得数据库中的username数据->通过userMapper
        return u;
    }
    @Override
    public void register(String username, String password) {
        String md5String = Md5Util.getMD5String(password);//对密码进行加密
        userMapper.add(username,md5String); // 在数据库中添加用户名何密码通过->userMapper
    }

    @Override
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> claims = ThreadLocalUtil.get();
        Integer id  = (Integer) claims.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newpwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer  id = (Integer) map.get("id");
        userMapper.updatePwd(Md5Util.getMD5String(newpwd),id);
    }


}
