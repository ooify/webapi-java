package me.ooify.api.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    public static final int CODE_SUCCESS = 200;
    public static final int CODE_ERROR = 500;
    private int code;
    private String msg;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    public Result setCode(int code) {
        this.code = code;
        return this;
    }

    public Result setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Result setData(Object data) {
        this.data = data;
        return this;
    }

    public static Result ok() {
        return new Result(CODE_SUCCESS, "ok", null);
    }

    public static Result ok(String msg) {
        return new Result(CODE_SUCCESS, msg, null);
    }

    public static Result ok(String msg, Object data) {
        return new Result(CODE_SUCCESS, msg, data);
    }

    public static Result error() {
        return new Result(CODE_ERROR, "error", null);
    }

    public static Result error(String msg) {
        return new Result(CODE_ERROR, msg, null);
    }


}
