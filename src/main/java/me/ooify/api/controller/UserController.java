package me.ooify.api.controller;


import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import me.ooify.api.entity.User;
import me.ooify.api.service.impl.UserServiceImpl;

import me.ooify.api.utils.Result;
import org.springframework.web.bind.annotation.*;


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
    public Result getUserById(@PathVariable Long id) {
        System.out.println(id);
        User user = userService.getById(id);
        return Result.ok("查询成功").setData(user);
    }

    @GetMapping("/username/{username}")
    public Result getUserByUsername(@PathVariable String username) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", username));
        return Result.ok("查询成功").setData(user);
    }

    @GetMapping("/email/{email}")
    public Result getUserByEmail(@PathVariable String email) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", email));
        return Result.ok("查询成功").setData(user);
    }

    @PostMapping
    public Result saveUser(@RequestBody User user) {
        boolean b = userService.save(user);
        if (b) {
            return Result.ok("添加成功");
        }
        return Result.error("添加失败");
    }

    @SaCheckLogin
    @PutMapping
    public Result updateUser(@RequestBody User user) {
        user.setId(StpUtil.getLoginIdAsLong());
        boolean b = userService.updateById(user);
        if (b) {
            return Result.ok("更新成功");
        }
        return Result.error("更新失败");
    }


}
