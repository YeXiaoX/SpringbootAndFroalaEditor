package com.demo.application;

/**
 * Created by Ivan on 2016/6/21.
 */
public class Response {
    private int code;
    private String msg;

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

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
