package org.zhiran.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zhiran.mapper.CategoryMapper;
import org.zhiran.pojo.Category;
import org.zhiran.pojo.Result;
import org.zhiran.service.CategoryService;
import org.zhiran.utils.ThreadLocalUtil;

import java.util.List;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public Category findById(Integer id) {
        Category c = categoryMapper.findById(id);
        return c;
    }

    @Override
    public void add(Category category) {
        Map<String, Object> map  = ThreadLocalUtil.get();
        Integer  userid= (Integer) map.get("id");
        category.setCreateUser(userid);
        categoryMapper.add(category);
    }

    @Override
    public List<Category> list() {
        Map<String,Object> map  = ThreadLocalUtil.get();
        Integer  userid = (Integer) map.get("id");

        return categoryMapper.list(userid);
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public void delete(Integer id) {
        Map<String , Object > map = ThreadLocalUtil.get();
        Integer userid = (Integer) map.get("id");
        categoryMapper.delete(userid,id);
    }
}
