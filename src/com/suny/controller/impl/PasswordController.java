package com.suny.controller.impl;

import com.suny.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 密码相关的类
 * 孙建荣
 * 2016/12/12 22:24
 */
@Controller
@RequestMapping("/password")
public class PasswordController {

    @Autowired
    private EmployeeService employeeService;


    /**
     * 进入修改密码页面
     *
     * @return 密码修改页面
     */
    @RequestMapping("/viewChangePassword")
    public String ViewChangePassword() {
        return "pages/adminView/changePassword";      //进入到修改密码页面
    }

    /**
     * ajax请求修改密码
     *
     * @param request  用户请求相关
     * @param response 服务器响应相关
     * @throws IOException 抛出IO流异常
     */
    @RequestMapping("/changePasswordByAjax")
    public void changePasswordByAjax(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();      //实例化一个输出对象
        String username = (String) request.getSession().getAttribute("username");   //获取请求中的username
        String oldPassword = request.getParameter("oldPassword");     //获取请求中的原始密码
        String newPassword = request.getParameter("newPassword");     //获取请求中的新密码
        String repeatPassword = request.getParameter("repeatPassword");   //获取请求中的再次输入新密码
        if (!newPassword.equals(repeatPassword)) {     //如果新密码不等于等于再次输入新密码
            printWriter.write("false");    //向页面发出响应
        } else {
            employeeService.checkPassword(username, oldPassword, newPassword);   //发送修改密码请求
            printWriter.write("true");    //向页面发送响应
        }
    }


    /**
     * 页面点击修改密码按钮修改密码controller请求
     * @param request   request请求
     * @param modelAndView   模型和视图对象
     * @return   返回处理结果
     */
    @RequestMapping("/changePassword")
    public ModelAndView changePassword(HttpServletRequest request, ModelAndView modelAndView) {
        String username = (String) request.getSession().getAttribute("username");    //获取请求中的用户名
        String oldPassword = request.getParameter("oldPassword");        //获取请求中的原始密码
        String newPassword = request.getParameter("newPassword");     //获取请求中的新密码
        String repeatPassword = request.getParameter("repeatPassword");    //获取请求中的再次输入新密码
        if (!newPassword.equals(repeatPassword)) {                       //如果新密码跟再次输入新密码不相等的话
            modelAndView.setView(new RedirectView("viewChangePassword"));      //重定向到修改密码页面
            modelAndView.addObject("error", "两次新密码不匹配");
        } else {
            employeeService.checkPassword(username, oldPassword, newPassword);    //两次密码相等就发送请求修改密码请求
            modelAndView.setView(new RedirectView("viewChangePassword"));
        }
        return modelAndView;         //重定向到ViewChangePassword请求
    }


}
