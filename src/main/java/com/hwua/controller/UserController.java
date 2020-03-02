package com.hwua.controller;

import com.hwua.pojo.User;
import com.hwua.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private IUserService us;

    @GetMapping("/logout")
    public String logout(HttpSession session) throws Exception{
        if(session!=null){
            session.invalidate();
        }
        return "redirect:/index.jsp";
    }

    @GetMapping("/findAllUsers1")
    public ModelAndView queryAllUsers1(@PathVariable(value = "sendid",required = false) String sendid) throws Exception{
        List<User> uList = us.getAllUsers();
        System.out.println(uList);
        ModelAndView mv = new ModelAndView();
        mv.addObject("uList",uList);
        mv.setViewName("newMsg");
        return mv;
    }

    @GetMapping("/findAllUsers2/{sendid}")
    public ModelAndView queryAllUsers2(@PathVariable(value = "sendid",required = false) String sendid) throws Exception{
        List<User> uList = us.getAllUsers();
        System.out.println(uList);
        ModelAndView mv = new ModelAndView();
        mv.addObject("sendid",sendid);
        mv.addObject("uList",uList);
        mv.setViewName("newMsg");
        return mv;
    }
}
