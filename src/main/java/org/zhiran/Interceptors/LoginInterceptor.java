package org.zhiran.Interceptors;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zhiran.pojo.Result;
import org.zhiran.utils.JwtUtil;
import org.zhiran.utils.ThreadLocalUtil;

import java.util.Map;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    //这个访问拦截器只是检测token能否被解析解析失败 则token错误 解析成功就成功了老弟
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            Map<String,Object> clamis = JwtUtil.parseToken(token);//校验token
            ThreadLocalUtil.set(clamis);
            return true;//通过
        } catch (Exception e) {
            response.setStatus(401);//http相应码为401
            return false;//拦截
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
       ThreadLocalUtil.remove();
    }
}
