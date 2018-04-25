package com.suny.controller;

import com.suny.entity.Employee;
import com.suny.service.impl.EmpManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 主要处理用户登陆登出请求
 * 孙建荣
 * 2016/12/12 13:03
 */
@Controller
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private EmpManagerService empManagerService;


    /**
     * 普通成员的退出
     * 因为管理员页面的退出是要整个窗口显示登陆页面，而用户页面只需要直接打开登陆页面就可以
     * @param request  request请求
     * @return   移除session中用户名后返回到登陆界面
     */
    @RequestMapping("/userLogout")
   public String userLogout(HttpServletRequest request){
       request.getSession().removeAttribute("username");   //移除session用户名
       return "redirect:/loginPage";    //返回登陆
   }

    /**
     *    处理用户的注销登陆请求，在注销的同时讲保存在session中的用户名进行注销处理
     * @param request  包含用户请求的相关信息
     */
    @RequestMapping("logout")
    public void logout(HttpServletRequest request) {
        request.getSession().removeAttribute("username");     //移除session中的username属性
    }


    /**
     * 当用户登陆成功，身份为普通成员身份后的请求
     *
     * @return 普通用户界面
     */
    @RequestMapping("userWeb")
    public String user_index() {
        return "pages/userView/user_index";
    }

    /**
     * 管理员用户登陆成功后，进入管理员页面
     *
     * @param request request请求，包含用户页面相关的属性
     * @return 管理员页面
     */
    @RequestMapping("adminWeb")
    public String admin_index(HttpServletRequest request) {
        String uri;       //定义一个跳转地址变量
        if (request.getSession().getAttribute("username") == null) {   //判断session中是否有记录，否则就重定向到登陆界面
            uri = "redirect:loginPage";                              //重定向到loginPage请求
        } else {
            uri = "pages/adminView/adminWeb";                      //找到session中username值时跳转地址
        }
        return uri;
    }

    /**
     * 处理ajax验证用户名的正确性
     *
     * @param request  request请求
     * @param response response请求
     * @return 对用户输入的用户名进行判断。正确就返回true，错误就返回false
     * @throws IOException 在进行向用户输出流的时候可能会发生IO流异常
     */
    @RequestMapping("checkLogin")
    public String checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter printWriter = response.getWriter();         //得到一个response输出对象
        String account = request.getParameter("account");        //获取表单ajax传来的用户账号
        Employee employee = empManagerService.getByEmployeeAccount(account);    //得到通过账号查询的user对象
        if (employee == null) {                 //当通过账号没有查询到user对象时
            printWriter.print("false");    //向页面发送字符串"false"
            return null;
        } else {
            printWriter.write("true");             //向前端页面发送字符串 "true"
            return null;
        }
    }

    /**
     * 处理用户的登陆请求
     *
     * @param account      登陆用户的账号名
     * @param password     登陆用户的密码
     * @param request      request请求
     * @param modelAndView Springmvc的模型和视图模型
     * @return 对应不同的用户角色进入不同的界面
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam(value = "account", required = true, defaultValue = "0") String account,
                              @RequestParam(value = "password", required = true, defaultValue = "0") String password,
                              HttpServletRequest request, ModelAndView modelAndView) {
        String verifyCode = (String) request.getSession().getAttribute("code");          //服务器向登陆页面发送的验证码
        String checkCode = request.getParameter("checkCode");             //用户填写的验证码
        if (!verifyCode.equals(checkCode) || checkCode.equals("")) {
            modelAndView.setView(new RedirectView("loginPage"));             //重定向到查看用户登陆页面
        } else {
            int status = empManagerService.validLogin(account, password);         //获取service判断用户名返回的状态值
            if (status == 0) {                                            //当查询到的数据为空时的操作
                modelAndView.setView(new RedirectView("loginPage"));          //重定向到查看用户登陆页面
            } else if (status == 1) {                   //当返回值为1或者2的时候,也就是账号密码匹配成功
                Employee employee = empManagerService.getByEmployeeAccount(account);    //查询id所对应的用户信息
                request.getSession().setAttribute("id", employee.getId());           //保存标示id到session中
                request.getSession().setAttribute("username", employee.getUsername());   //把用户名保存到session里面
                request.getSession().setAttribute("role", "user");                       //保存用户角色到session中
                modelAndView.setView(new RedirectView("userWeb"));   //重定向到普通会员的主页面
            } else {
                Employee employee = empManagerService.getByManagerAccount(account);    //查询id所对应的用户信息
                request.getSession().setAttribute("username", employee.getUsername());   //把用户名保存到session里面
                request.getSession().setAttribute("role", "admin");                       //保存用户角色到session中
                modelAndView.setView(new RedirectView("adminWeb"));   //重定向到管理员的主页面
            }
        }

        return modelAndView;
    }

    /**
     * 进入登陆页面
     *
     * @return 登陆页面
     */
    @RequestMapping("/loginPage")
    public String ViewLogin() {
        return "redirect:/index.jsp";   //重定向到根目录学下index.jsp页面
    }
}
