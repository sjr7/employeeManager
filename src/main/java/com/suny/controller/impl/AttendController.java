package com.suny.controller.impl;

import com.suny.service.impl.AttendService;
import com.suny.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 显示考勤记录
 * 孙建荣
 * 2016/12/12 22:23
 */
@Controller
@RequestMapping("/attend")
public class AttendController {

    @Autowired
    private AttendService attendService;

    private Page page;




    /**
     * 使用关键字进行查询考勤记录
     *
     * @param request  request对象，用户请求相关
     * @param modelMap 用于保存属性
     * @return 返回通过关键词查询出来的数据并展现在考勤记录列表里面
     * @throws UnsupportedEncodingException 处理不支持设置指定编码的异常
     */
    @RequestMapping("/getByName")
    public String getByName(HttpServletRequest request, ModelMap modelMap) throws UnsupportedEncodingException {
        int pageCount = 5;                //设置每一页行数
        String currentPage = request.getParameter("currentPage");   //获取当前页
        if (currentPage == null || currentPage.equals("0")) {        //默认为第一页
            currentPage = "1";
        }
        request.setCharacterEncoding("UTF-8");     //设置编码
        String username = request.getParameter("name");     //获取请求参数
        Page page = attendService.getByName(username, pageCount, Integer.valueOf(currentPage));   //返回分页后的page对象
        List attendRecordList = page.getPageDate();    //获取分页数据
        modelMap.addAttribute("attendRecordList", attendRecordList);    //添加查询的数据到modelMap中
        modelMap.addAttribute("value", username);     //添加name到modelMap中
        modelMap.addAttribute("page", page);     //添加page对象到modelMpa
        return "/pages/adminView/attendanceRecord";
    }

    /**
     * 进入考勤记录列表
     *
     * @param modelMap 向页面传送考勤记录值
     * @return 考勤记录页面
     */
    @RequestMapping("/attendRecord")
    public String attendRecord(HttpServletRequest request, ModelMap modelMap) throws UnsupportedEncodingException {
        int pageCount = 5;                                           //分页每页显示的行数
        String currentPage = request.getParameter("currentPage");       //获取当前页页码
        if (currentPage == null || currentPage.equals("0")) {         //如果当前页为空或者为0的话默认为第1页
            currentPage = "1";
        }
        if (Integer.valueOf(currentPage) > attendService.getMaxPage(pageCount)) {   //如果当前页大于总页数则跳转到最大分页数
            page = attendService.getAllRecord(attendService.getMaxPage(pageCount), pageCount);
        } else {
            page = attendService.getAllRecord(Integer.valueOf(currentPage), pageCount);     //把当前页跟每页行数传到service里面进行分页查询
        }
        modelMap.addAttribute("page", page);            //添加分页对象到modelMap里面
        List attendRecordList = page.getPageDate();        //获取page对象里面的考勤记录数据
        modelMap.addAttribute("attendRecordList", attendRecordList);     //把考勤记录数据吧保存到session中
        return "/pages/adminView/attendanceRecord";      //考勤记录列表
    }

}
