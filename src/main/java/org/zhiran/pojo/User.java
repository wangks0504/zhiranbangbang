package org.zhiran.pojo;
import jdk.jfr.DataAmount;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.time.LocalDateTime;
@Data
public class User {
    private Integer id;
    private String username;
    @JsonIgnore//让SpringMvc把当前对象转换为json字符串的时候 忽略password 最终的json字符串中就没有password这个属性了
    private String password;
    private String nickname;
    private String email;
    private String userPic;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}