package org.zhiran.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhiran.mapper.UserMapper;
import org.zhiran.pojo.User;
import org.zhiran.utils.Md5Util;

@Service
public class UserServiceImpl implements org.zhiran.service.UserService {
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



}
