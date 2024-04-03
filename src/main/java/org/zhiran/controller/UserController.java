package org.zhiran.controller;

import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhiran.pojo.Result;
import org.zhiran.pojo.User;
import org.zhiran.service.UserService;
import org.zhiran.utils.JwtUtil;
import org.zhiran.utils.Md5Util;
import java.util.HashMap;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/user")//user相关的处理操作
public class UserController {
    @Autowired
    private UserService userService ;
    @PostMapping("/register")//注册用户
    public Result register(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$")String password){
        User u =userService.findByUsername(username);//检测用户名是否存在通过->调用server
        if (u == null){
            userService.register(username,password);//注册用户通过->调用server
            return Result.success();
        }else {
            return Result.error("您的用户名已经存在");
        }
    }
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$")String username,@Pattern(regexp = "^\\S{5,16}$") String password){
        User loginUser = userService.findByUsername(username);//检测用户名称是否存在->调用server
        if (loginUser == null){
            return Result.error("用户名输入错误");
        }
        Map<String, Object> claim = new HashMap<>();//创建Map集合
        claim.put("id",loginUser.getId());
        claim.put("username",loginUser.getUsername());
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){//验证密码加密后是否相等
            String token = JwtUtil.genToken(claim);//生成token
            return  Result.success(token);
        }
        return Result.error("密码错误");
    }
    @GetMapping("/userInfo")
    public Result<User> userInfo (@RequestHeader("Authorization")String token){
        Map<String ,Object> map = JwtUtil.parseToken(token);
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }
}
