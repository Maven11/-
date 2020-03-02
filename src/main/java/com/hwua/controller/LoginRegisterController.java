package com.hwua.controller;

import com.hwua.pojo.User;
import com.hwua.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

//@Controller
//@ResponseBody

@RestController
public class LoginRegisterController {
    @Autowired
    private IUserService us;
    @GetMapping("/login")
    public Map<String,Object> login(User user, String code, HttpSession session) throws Exception{
        Map<String, Object> map = new HashMap<>();
        String vcode = (String)session.getAttribute("code");//生成的验证码
        session.removeAttribute("code");//删除先前生成的验证码,因为验证码只能用一次
        if (vcode == null || !vcode.equals(code)) {
            //把错误信息放到request域中
            map.put("info", "");
            map.put("error", "验证码出错");
        } else {

            //调用业务层
            try {
                user = us.login(user);
                //登录成功
                if (user != null) {
                    session.setAttribute("user", user);
                    //调用Servlet,获取当前登录用户所收到的所有短消息
                    map.put("info", "success");

                } else {
                    map.put("info", "");
                    map.put("error", "用户名或密码出错");

                }
            } catch (Exception e) {
                map.put("info", "");
                map.put("error", "联系管理员");
                e.printStackTrace();
            }
        }
        return map;
    }

    @PostMapping("/register")
    public Map<String,Object> register(User user) throws Exception{
        Map<String, Object> map = new HashMap<>();
        try {
            boolean flag = us.register(user);//调用业务方法
            if (flag) {
                map.put("info", "注册成功");
            } else {
                map.put("info", "注册失败");
            }
        } catch (Exception e) {
            map.put("info", "注册失败");
            e.printStackTrace();
        }
        return map;
    }

    @GetMapping("/validateUser")
    public Map<String,Object> validateUser(String name) throws Exception{
        Map<String, Object> map = new HashMap<>();
        boolean flag = us.queryUserByName(name);
        map.put("info",flag);
        return map;
    }












}
