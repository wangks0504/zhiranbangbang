package org.zhiran.mapper;

import org.apache.ibatis.annotations.*;
import org.zhiran.pojo.Event;

import java.util.List;

@Mapper
public interface EventMapper {
    @Insert("insert into event (title,content,cover_img,state,category_id,create_user,create_time,update_time) values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},now(),now())")
    void add(Event event);

    List<Event> list(Integer userId, String categoryId, String state);
    @Select("select * from  event where id=#{id}")
    Event detail(Integer id);
    @Update("update event set title = #{title} ,content = #{content} , cover_img=#{coverImg},state = #{state} ,update_time=now() where create_user=#{createUser} and id = #{id}")
    void update(Event event);
    @Delete("delete  from event where create_user=#{userid} and id =#{id}")
    void delete(Integer userid, Integer id);
}
