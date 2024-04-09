package org.zhiran.service;

import org.zhiran.pojo.User;

public interface UserService {
    User findByUsername(String username); //在数据库中寻找已经存在的用户名

    void register(String username, String password);//在数据库中通过用户输入的用户名和密码进行注册

    void update(User user);

    void updateAvatar(String avatarUrl);

    void updatePwd(String newpwd);
}
