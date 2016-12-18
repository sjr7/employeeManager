package com.suny.controller.impl;

import com.suny.service.impl.EmpManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员工类
 * 孙建荣
 * 2016/12/12 13:02
 */
@RequestMapping("/manager")
@Controller
public class ManagerController {

    @Autowired
    private EmpManagerService empManagerService;

    /**
     * 为所有普通成员添加缺勤记录
     */
    @RequestMapping("/punch")
    public void punch(){
        empManagerService.autoPunch();
    }
}









