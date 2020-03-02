package com.hwua.mapper;

import com.hwua.pojo.User;

import java.util.List;

public interface UserMapper {
    public User queryUserByUser(User user) throws Exception;//登录
    public Integer saveUser(User user) throws Exception;;//注册
    public List<User> queryAllUsers() throws Exception;; //查询所有的用户
    public User queryUserBySendid(Long sendid) throws Exception;; //查询发送者用户信息
    public User queryUserByName(String name) throws Exception;;//判断用户名是否已经注册
}
