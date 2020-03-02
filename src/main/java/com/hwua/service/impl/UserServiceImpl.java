package com.hwua.service.impl;

import com.hwua.mapper.UserMapper;
import com.hwua.pojo.User;
import com.hwua.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) throws Exception {
        return userMapper.queryUserByUser(user);
    }
    @Override
    public boolean register(User user) throws Exception {
        return userMapper.saveUser(user) == 1 ? true : false;
    }

    @Override
    public List<User> getAllUsers() throws Exception {
        return userMapper.queryAllUsers();
    }


    @Override
    public boolean queryUserByName(String name) throws Exception {
        return userMapper.queryUserByName(name) != null ? true : false;
    }
}

