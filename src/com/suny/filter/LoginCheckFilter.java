package com.suny.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 原生Filter拦截未登陆用户请求
 * 孙建荣
 * 2016/12/16 13:17
 */
public class LoginCheckFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 判断是否有session中的username属性，如果没有泽判定为未登陆
     * @param servletRequest   request请求
     * @param servletResponse   服务器的响应
     * @param filterChain     下一拦截请求
     * @throws IOException  抛出IO流异常
     * @throws ServletException    抛出Servlet异常
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;    //强转为HttpServletRequest
        HttpServletResponse response = (HttpServletResponse) servletResponse;  //强转为HttpServletResponse
        HttpSession session = request.getSession();       //获取sessioni对象
        String username = (String) session.getAttribute("username");   //获取session中的username
        if (username != null) {
            filterChain.doFilter(request,response);    //为空则重定向到登陆页面
        }
        else{
            response.sendRedirect("index.jsp");
        }

    }

    @Override
    public void destroy() {

    }
}
