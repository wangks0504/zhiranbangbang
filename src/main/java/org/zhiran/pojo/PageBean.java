package org.zhiran.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//改文件用于分页查询提供数据对象
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean <T>{
    private long tatal;//总条数
    private List<T> items;//当前页数据集合
}
