package me.ooify.api.config;

import cn.dev33.satoken.exception.NotLoginException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import me.ooify.api.utils.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public Result handleRuntimeException(RuntimeException e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(ServletException.class)
    public Result handleServletException(ServletException e) {
        return Result.error(e.getMessage());
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public Result handleNotFound(HttpServletRequest request) {
        return Result.error("请求访问：" + request.getRequestURI() + "，接口不存在")
                .setCode(404);
    }

    @ExceptionHandler(NotLoginException.class)
    public Result handlerNotLoginException(NotLoginException e, HttpServletRequest request) {
        String message = "";
        if (e.getType().equals(NotLoginException.NOT_TOKEN)) {
            message = "未提供token";
        } else if (e.getType().equals(NotLoginException.INVALID_TOKEN)) {
            message = "token无效";
        } else if (e.getType().equals(NotLoginException.TOKEN_TIMEOUT)) {
            message = "token已过期";
        } else if (e.getType().equals(NotLoginException.BE_REPLACED)) {
            message = "token已被顶下线";
        } else if (e.getType().equals(NotLoginException.KICK_OUT)) {
            message = "token已被踢下线";
        } else {
            message = "当前会话未登录";
        }
        Map<String, Object> error = new HashMap<>();
        error.put("error", message);
        return Result.error("请求访问：" + request.getRequestURI() + "，认证失败，无法访问系统资源")
                .setCode(401)
                .setData(error);
    }
}
