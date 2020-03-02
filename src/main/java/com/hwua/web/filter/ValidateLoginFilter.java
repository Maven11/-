package com.hwua.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/*@WebFilter("/*")*/
public class ValidateLoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        //验证当前是否登录成功了,假如登录成功了,直接放行,没有登录成功,跳转到登录页面
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.contains("/index.jsp")|| uri.contains("/images") || uri.contains("/register.jsp") || uri.contains("/lr.do") || uri.contains("/css") || uri.contains("/scripts") || uri.contains("/validateCode")) {
            filterChain.doFilter(req, resp);// 放行
        } else {
            HttpSession session = req.getSession(false);
            //没有登录成功
            if (session == null || session.getAttribute("user") == null) {
                resp.sendRedirect(req.getContextPath() + "/index.jsp");
            } else {
                filterChain.doFilter(req, resp);// 放行
            }
        }
    }

    @Override
    public void destroy() {

    }
}
