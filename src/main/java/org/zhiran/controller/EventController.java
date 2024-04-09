package org.zhiran.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhiran.pojo.Event;
import org.zhiran.pojo.Result;
import org.zhiran.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;
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
}
    @PostMapping("/add")
    public Result add (@RequestBody Event event){
        eventService.add(event);
      return   Result.success("文章添加成功");
    }
}
