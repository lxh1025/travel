package com.lxh.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lxh.mapper.UserMapper;
import com.lxh.pojo.User;
import com.lxh.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
