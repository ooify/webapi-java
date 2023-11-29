package me.ooify.api.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import cn.dev33.satoken.util.SaResult;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import me.ooify.api.entity.User;
import me.ooify.api.service.impl.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author jonsan
 * @since 2023-11-27
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    UserServiceImpl userService;

    @GetMapping("/{id}")
    public SaResult getUserById(@PathVariable Long id) {
        System.out.println(id);
        User user = userService.getById(id);
        return SaResult.ok("查询成功").setData(user);
    }

    @GetMapping("/username/{username}")
    public SaResult getUserByUsername(@PathVariable String username) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        return SaResult.ok("查询成功").setData(user);
    }

    @GetMapping("/email/{email}")
    public SaResult getUserByEmail(@PathVariable String email) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", email));
        return SaResult.ok("查询成功").setData(user);
    }

    @PostMapping
    public SaResult saveUser(@RequestBody User user) {
        boolean b = userService.save(user);
        if (b) {
            return SaResult.ok("添加成功");
        }
        return SaResult.error("添加失败");
    }

    @SaCheckLogin
    @PutMapping
    public SaResult updateUser(@RequestBody User user) {
        user.setId(StpUtil.getLoginIdAsLong());
        boolean b = userService.updateById(user);
        if (b) {
            return SaResult.ok("更新成功");
        }
        return SaResult.error("更新失败");
    }


}
