package org.zhiran.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zhiran.pojo.Result;
import org.zhiran.utils.JwtUtil;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticalController {
    @GetMapping("/list")
    public Result<String> list (@RequestHeader(name = "Authorization") String token , HttpServletResponse response){
//        try {
//            Map<String,Object> clamis = JwtUtil.parseToken(token);
//            return Result.success("需求帮助数据");
//        } catch (Exception e) {
//            response.setStatus(401);
//            return Result.error("你是不是不想登录就直接白嫖");
//        }
//    }
     return  Result.success("需求");
}}
