package org.zhiran.service;

import org.zhiran.pojo.Category;

import java.util.List;

public interface CategoryService {


    Category findById(Integer id);

    void add(Category category);

    List<Category> list();

    void update(Category category);

    void delete(Integer id);
}
