package com.sky.common.exception;

import com.sky.common.data.BaseResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**全局异常处理*/
@RestControllerAdvice
public class HopeExceptionHandler {


    /**其他异常*/
    @ExceptionHandler(Exception.class)
    public BaseResponse doHandler(Exception e){
        return BaseResponse.error(e.getMessage());
    }
}
