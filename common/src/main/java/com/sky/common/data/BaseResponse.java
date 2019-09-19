package com.sky.common.data;

import com.sky.common.contant.Contant;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.HashMap;

/**
 * 统一响应封装类
 * */
@Data
@AllArgsConstructor
public class BaseResponse {

    private String msg;
    private String code;
    private HashMap<String,Object> data;

    public BaseResponse(String msg,String code){
        this.msg = msg;
        this.code = code;
    }

    public static BaseResponse ok(){
        return new BaseResponse("success",Contant.success);
    }

    public static BaseResponse ok(HashMap<String,Object> data){
        return new BaseResponse("success",Contant.success,data);
    }

    public static BaseResponse error(String msg){
        return new BaseResponse(msg, Contant.INTERNAL_SERVER_ERROR);
    }
}
