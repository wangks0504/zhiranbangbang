package org.zhiran.Interceptors;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.ibatis.plugin.Interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.zhiran.pojo.Result;
import org.zhiran.utils.JwtUtil;
import java.util.Map;
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            Map<String,Object> clamis = JwtUtil.parseToken(token);//校验token
            return true;//通过
        } catch (Exception e) {
            response.setStatus(401);//http相应码为401
            return false;//拦截
        }
    }

}
