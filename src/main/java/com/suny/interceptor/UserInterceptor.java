package com.suny.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限拦截器，对未登陆的用户进行拦截
 * 孙建荣
 * 2016/12/15 22:47
 */
public class UserInterceptor implements HandlerInterceptor {


    /**
     *    还没有进行处理请求的时候的回调
     * @param httpServletRequest    request对象相关属性
     * @param httpServletResponse   response对象，服务器对用户得到响应请求
     * @param o     Object对象，在被类中为username对象
     * @return    对象用户登陆请求进行判断，有session就放行，没有session就拦截，不进行下一操作
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        Object username=httpServletRequest.getSession().getAttribute("username");     //判断session中是否存在username
        if(username==null){                                                    //为空则重定向到登陆页面
            httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/login");
            return false;     //返回false则不提交给下一处理
        }
        return true;    //判断成功的话就返回true，交由下一处理器进行处理
    }

    /**
     *  处理完用户请求，但是还没有进行页面渲染
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    /**
     * 整个请求完毕的回调，视图也已经渲染的时候
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
