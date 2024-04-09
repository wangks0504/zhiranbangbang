package org.zhiran.Exception;

import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zhiran.pojo.Result;

@RestControllerAdvice
public class GlobleExceptionHander {
    @ExceptionHandler(Exception.class)
    public Result GlobleExceptionHander(Exception e){
        e.printStackTrace();
        return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage() : "数据处理异常");
    }
}
