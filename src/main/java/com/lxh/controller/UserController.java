package com.lxh.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lxh.common.Result;
import com.lxh.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.lxh.service.UserService;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public Result<String> login(@RequestBody User user, HttpServletRequest request) {
        System.out.println("login...");
        System.out.println(user);
        //拿到验id和密码
        String name = user.getName();
        String password = user.getPassword();
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName, name);
        User userCheck = userService.getOne(lambdaQueryWrapper);

        if (userCheck == null) {
            return Result.error("用户不存在，请注册账号");
        } else {
            if (password.equals(userCheck.getPassword())) {
                request.getSession().setAttribute("user", name);
                return Result.success("登陆成功，欢迎~");
            }else {
                return Result.error("密码错误");
            }
        }
    }

    @PostMapping("/logout")
    public Result<String> logout(@RequestBody User user,HttpServletRequest request){
        System.out.println("logout");
        request.getSession().removeAttribute(user.getName());
        return Result.success("退出成功");
    }
    @PostMapping("/register")
    public Result<String> register(@RequestBody User user) {
        String name = user.getName();
        String password = user.getPassword();
        System.out.println(user);
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (name == null || password == null) {
            return Result.error("用户名或密码不能为空");
        }
        lambdaQueryWrapper.eq(User::getName, name);
        if (userService.getOne(lambdaQueryWrapper) != null) {
            return Result.error("用户名已存在");
        }
        userService.save(user);
        return Result.success("注册成功");

    }
    @GetMapping("/getInfo")
    public Result<User> GetInfo(@RequestParam("username") String name){
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getName, name);
        User user = userService.getOne(lambdaQueryWrapper);
        return Result.success(user);
    }
}
