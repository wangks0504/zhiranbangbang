package org.zhiran.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhiran.pojo.Category;
import org.zhiran.pojo.Result;
import org.zhiran.service.CategoryService;
import org.zhiran.utils.ThreadLocalUtil;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @PostMapping()
    public Result add(@RequestBody @Validated(Category.Add.class) Category category){
        categoryService.add(category);
        return Result.success("成功添加分类");
    }
    @GetMapping
    public Result<List<Category>> list (){
        List<Category> ls = categoryService.list();
        return Result.success(ls);
    }
    @GetMapping("/detail")
    public Result<Category> detail(Integer id){
        Category c = categoryService.findById(id);
        return Result.success(c);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success("更新成功");
    }
    @DeleteMapping("/delete")
    public Result delete(Integer id){
        categoryService.delete(id);
        return Result.success("删除成功");
    }

}
