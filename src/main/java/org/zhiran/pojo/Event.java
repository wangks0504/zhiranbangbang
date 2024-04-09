package org.zhiran.pojo;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Event {
    private Integer id;
    private String title;
    private String content;
    private String coverImg;
    private String state;
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
