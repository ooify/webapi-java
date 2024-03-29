package me.ooify.api.controller;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import me.ooify.api.entity.User;
import me.ooify.api.service.impl.UserServiceImpl;
import me.ooify.api.utils.Result;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@Transactional
public class LoginController {
    @Resource
    private UserServiceImpl userSeurvice;


    @PostMapping("/login")
    public Result login(@RequestBody(required = false) Map<String, String> user) {
        if (user != null) {
            String username = user.get("username");
            String password = user.get("password");
            User u = userSeurvice.getOne(new QueryWrapper<User>().eq("username", username).eq("password", password));
            if (u != null) {
                StpUtil.login(u.getId());
                return Result.ok("登录成功").setData(StpUtil.getTokenValue());
            } else {
                return Result.error("用户不存在/密码错误");
            }
        } else return Result.error("请传入必要参数");
    }

    @PostMapping("/logout")
    public Result logout() {
        StpUtil.logoutByTokenValue(StpUtil.getTokenValue());
        return Result.ok("登出成功");
    }

    @PostMapping("/register")
    public Result register(@RequestBody(required = false) User user) {
        if (user != null) {
            if (user.getUsername() == null) {
                return Result.error("参数错误，请传入用户名！");
            } else if (user.getPassword() == null) {
                return Result.error("参数错误，请传入密码！");
            } else if (user.getEmail() == null) {
                return Result.error("参数错误，请传入邮箱！");
            } else {
                if (userSeurvice.getOne(new QueryWrapper<User>().eq("username", user.getUsername())) != null) {
                    return Result.error("新增用户'" + user.getUsername() + "'失败，登录账号已存在");
                } else if (userSeurvice.getOne(new QueryWrapper<User>().eq("username", user.getEmail())) != null) {
                    return Result.error("新增用户'" + user.getUsername() + "'失败，邮箱已存在");
                } else {
                    if (userSeurvice.save(user)) {
                        return Result.ok("新增用户'" + user.getUsername() + "'成功");
                    } else {
                        return Result.error("新增用户'" + user.getUsername() + "'失败");
                    }
                }
            }
        } else return Result.error("请传入必要参数");
    }
}
