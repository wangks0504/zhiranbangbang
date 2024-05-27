package org.zhiran.pojo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;
import org.zhiran.anno.State;

import java.time.LocalDateTime;
@Data
public class Event {
    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @URL
    private String coverImg;
    @State
    private String state;
    private Integer categoryId;
    private Integer createUser;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


}
