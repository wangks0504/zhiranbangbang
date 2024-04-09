package org.zhiran.controller;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.mockito.internal.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhiran.pojo.Result;
import org.zhiran.pojo.User;
import org.zhiran.service.UserService;
import org.zhiran.utils.JwtUtil;
import org.zhiran.utils.Md5Util;
import org.zhiran.utils.ThreadLocalUtil;
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
        claim.put("id",loginUser.getId());//把用户id和username放进claim集合
        claim.put("username",loginUser.getUsername());
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){//验证密码加密后是否相等
            String token = JwtUtil.genToken(claim);//生成token
            return  Result.success(token);
        }
        return Result.error("密码错误");
    }
    @GetMapping("/userInfo")
    public Result<User> userInfo (@RequestHeader("Authorization")String token){
        Map<String ,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }
    @PutMapping("/update")
    public Result update(@RequestBody @Validated User user){
        userService.update(user);
        return Result.success("信息已经重新修改");
    }
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl){
    userService.updateAvatar(avatarUrl);
    return Result.success("设置头像成功");
    }
    @PatchMapping("/updatePwd")
    public Result updatePwd (@RequestBody Map<String ,String> updatePwd){
        String oldpwd = updatePwd.get("old_pwd");
        String newpwd = updatePwd.get("new_pwd");
        String repwd = updatePwd.get("re_pwd");
        if(!StringUtils.hasLength(oldpwd) || !StringUtils.hasLength(newpwd)||!StringUtils.hasLength(repwd)){
            return Result.error("你是不是有一个密码没有填写?");
        }
        if(!repwd.equals(newpwd)){
            return Result.error("你这两次输入的新密码不一样啊");
        }
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginName = userService.findByUsername(username);
        if (!loginName.getPassword().equals(Md5Util.getMD5String(oldpwd))){
            return  Result.error("你输入的旧密码和你原来的旧密码不一样哦");
        }
        userService.updatePwd(newpwd);
        return Result.success("你的密码已经成功更改喽别再忘记了哈");
    }
}
