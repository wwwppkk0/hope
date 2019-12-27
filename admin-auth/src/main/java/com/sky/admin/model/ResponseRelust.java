package com.sky.admin.model;

import org.springframework.http.HttpStatus;

public class ResponseRelust {

    private String code;
    private String msg;
    private Object data;

    public ResponseRelust(){
        code="0";
        msg="success";
    }

    public ResponseRelust(Object data){
        this();
        this.data=data;
    }

    public ResponseRelust(String code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseRelust ok(Object data){
        return new ResponseRelust(data);
    }

    public static ResponseRelust error(String msg){
        return new ResponseRelust(HttpStatus.INTERNAL_SERVER_ERROR.toString(),msg,null);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
