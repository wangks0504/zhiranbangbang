package org.zhiran.service;

import org.zhiran.pojo.Event;
import org.zhiran.pojo.PageBean;

public interface EventService {

    void add(Event event);
    PageBean<Event> list(Integer pageNum, Integer pageSize, String categoryId, String state);

    Event detail(Integer id);

    void update(Event event);

    void delete(Integer id);
}
