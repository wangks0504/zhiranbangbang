package org.zhiran.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhiran.controller.EventController;
import org.zhiran.mapper.EventMapper;
import org.zhiran.pojo.Event;
import org.zhiran.service.EventService;
import org.zhiran.utils.ThreadLocalUtil;

import java.util.Map;

@Service
public class EventServiceImpl  implements EventService {
    @Autowired
    private EventMapper eventMapper;
    @Override
    public void add(Event event) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        event.setCreateUser(userId);
        eventMapper.add(event);
    }
}
