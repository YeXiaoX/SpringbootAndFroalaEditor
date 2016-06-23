package com.demo.application;

/**
 * Created by Ivan on 2016/6/21.
 */
public class Response {
    private int code;
    private String msg;
    private Object data;

    public Response(int code, String msg, Object object) {
        this.code = code;
        this.msg = msg;
        this.data = object;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
