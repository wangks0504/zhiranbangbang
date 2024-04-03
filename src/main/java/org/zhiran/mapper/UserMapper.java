package org.zhiran.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
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
    //把用户名和密码传入数据库中
}
