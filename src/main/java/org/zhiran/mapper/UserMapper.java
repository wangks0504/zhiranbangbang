package org.zhiran.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Service;
import org.zhiran.pojo.User;
@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username}")
    User findByUsername(String username);
    //根据用户传入数据进行查找用户名
    @Insert("insert into user (username,password,create_time,update_time)"+
            "values (#{username},#{md5password},now(),now())")
    void add(String username, String md5password);
    @Update("update user set nickname =#{nickname},email = #{email} , update_time =now() where id = #{id}  ")
     void update(User user);
    @Update(("update user set user_pic = #{avatarUrl},update_time=now() where id = #{id}"))
    void updateAvatar(String avatarUrl, Integer id);
    @Update("update user set password= #{newpwd} , update_time =now() where id = #{id}")
    void updatePwd(String newpwd,Integer id);
    //把用户名和密码传入数据库中
}
