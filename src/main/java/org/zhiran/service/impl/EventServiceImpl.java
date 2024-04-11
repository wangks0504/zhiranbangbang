package org.zhiran.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhiran.mapper.EventMapper;
import org.zhiran.pojo.Event;
import org.zhiran.pojo.PageBean;
import org.zhiran.service.EventService;
import org.zhiran.utils.ThreadLocalUtil;
import java.util.List;
import java.util.Map;

@Service
public class EventServiceImpl  implements EventService {
    @Autowired
    private EventMapper eventMapper;

    @Override
    public void add(Event event) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        event.setCreateUser(userId);
        eventMapper.add(event);
    }

    @Override
    public PageBean<Event> list(Integer pageNum, Integer pageSize, String categoryId, String state) {
        //创建一个分页查询的对象
        PageBean<Event> pb = new PageBean<>();
        PageHelper.startPage(pageNum,pageSize);

        //执行mapper函数调用
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Event> ls = eventMapper.list(userId,categoryId,state);//保存一下数据库中列出的数据
        Page<Event> pageEvent = (Page<Event>) ls;
        pb.setTatal(pageEvent.getTotal());
        pb.setItems(pageEvent.getResult());//注意这里的Items 表示当前页数据集合
        return pb;
    }

    @Override
    public Event detail(Integer id) {
       Event ev =  eventMapper.detail(id);
        return ev;
    }

    @Override
    public void update(Event event) {
        Map<String,Object> map  = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        event.setCreateUser(userid);
        eventMapper.update(event);
    }

    @Override
    public void delete(Integer id) {
        Map<String,Object> map  = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        eventMapper.delete(userid , id);
    }
}

