package com.hwua.service;

import java.util.List;

import com.hwua.pojo.User;

/**
 * @author Administrator
 */
public interface IUserService {
    public User login(User user) throws Exception;//登录

    public boolean register(User user) throws Exception;//注册

    public List<User> getAllUsers() throws Exception; //查询所有的用户

    public boolean queryUserByName(String name) throws Exception;//判断用户名是否已经注册

}
