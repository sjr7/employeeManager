package com.suny.controller;

import com.suny.service.impl.ApproveService;
import com.suny.utils.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

/**
 * 对成员的考勤记录存在有异议的申请进行处理
 * 孙建荣
 * 2016/12/13 17:27
 */
@Controller
@RequestMapping("/approve")
public class ApproveController {


    @Autowired
    private ApproveService approveService;

    private Page page;

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }


    /**
     * 对申请进行处理
     * @param request  包含同意或者拒绝的参数
     * @return   返回到原页面
     * @throws Exception
     */
    @RequestMapping("/checkApprove")
    public String checkApprove(HttpServletRequest request) throws Exception {
        String mgrName = (String) request.getSession().getAttribute("username");  //获取session中保存的管理员的名字
        String result = request.getParameter("result");   //审批的结果
        Integer appid = Integer.valueOf(request.getParameter("appid"));    //获取要修改的考勤申请id
        //通过申请时的处理
        if (result.equals("pass")) {
            approveService.check(appid, mgrName, true);
        } else if (result.equals("deny")) {
            approveService.check(appid, mgrName, false);
        } else {
            throw new Exception("参数丢失");
        }
        return "redirect:/approve/approveList";    //重定向到待申请修改考勤记录列表

    }

    /**
     * 考勤审批页面
     *
     * @return
     */
    @RequestMapping("/approveList")
    public String approveList(ModelMap modelMap, HttpServletRequest request) throws UnsupportedEncodingException {
        int pageCount = 5;                                           //分页每页显示的行数
        String currentPage = request.getParameter("currentPage");       //获取当前页页码
        if (currentPage == null || currentPage.equals("0")) {         //如果当前页为空或者为0的话默认为第1页
            currentPage = "1";
        }
        if (Integer.valueOf(currentPage) > approveService.getMaxPage(pageCount)) {   //如果当前页大于总页数则跳转到最大分页数
            page = approveService.getApproveList(approveService.getMaxPage(pageCount), pageCount);
        } else {
            page = approveService.getApproveList(Integer.valueOf(currentPage), pageCount);     //把当前页跟每页行数传到service里面进行分页查询
        }
        modelMap.addAttribute("approveList", page.getPageDate());            //添加分页对象到modelMap里面
        return "/pages/adminView/approveList";
    }

}
