package com.example.station_springboot.common;

import lombok.Data;


@Data
public class Result {
    private int code;
    private String message;
    private Object data;

    public Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result success() {
        return new Result(200, "Success", null);
    }

    // 成功返回结果
    public static Result success(Object data) {
        return new Result(200, "Success", data);
    }

    // 错误返回结果
    public static Result error(int code, String message) {
        return new Result(code, message, null);
    }
}
