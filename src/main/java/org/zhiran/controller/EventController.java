package org.zhiran.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.zhiran.pojo.Event;
import org.zhiran.pojo.PageBean;
import org.zhiran.pojo.Result;
import org.zhiran.service.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
    @Autowired
    private EventService eventService;
//    @GetMapping("/list")
//    public Result<String> list (@RequestHeader(name = "Authorization") String token , HttpServletResponse response){
////        try {
////            Map<String,Object> clamis = JwtUtil.parseToken(token);
////            return Result.success("需求帮助数据");
////        } catch (Exception e) {
////            response.setStatus(401);
////            return Result.error("你是不是不想登录就直接白嫖");
////        }
////    }
//     return  Result.success("需求");
//}
    @PostMapping("/add")
    public Result add (@RequestBody @Validated Event event){
        eventService.add(event);
      return   Result.success("任务添加成功");
    }
    @GetMapping()
    public Result<PageBean<Event>> pageBeanResult(Integer pageNum,Integer pageSize ,
                                                  @RequestParam(required = false) String categoryId,
                                                  @RequestParam(required = false) String state){
       PageBean<Event> pb = eventService.list(pageNum,pageSize,categoryId,state);
        return Result.success(pb);//这里返回的pb是PageBean<Event> 的实例 就像开头所说的那样 Result<PageBean<Event>> 好美哈哈哈,不过周亚平👌更美嘿嘿;
    }
    @GetMapping("/detail")
    public Result<Event> detail (Integer id){//用于查询文章详细信息
       Event ev =  eventService.detail(id);
       return Result.success(ev);
    }
    @PutMapping("/update")//更新任务操作
    public Result update(@RequestBody @Validated Event event){
        eventService.update(event);
        return Result.success("你的任务已经修改成功");
    }
    @DeleteMapping("/delete")//删除任务操作
    public Result delete ( Integer id ){
        eventService.delete(id);
       return Result.success("你的任务已经成功删除");
    }
}
