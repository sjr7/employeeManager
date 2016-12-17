package com.suny.service.impl;

import com.suny.dao.impl.EmployeeDao;
import com.suny.dao.impl.ManagerDao;
import com.suny.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 成员管理相关的代码
 * 孙建荣
 * 2016/12/12 13:01
 */
@Service
public class EmpManagerService {
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private EmployeeDao employeeDao;


    /**
     * 查询普通成员账号对应的信息
     *
     * @param account 要查询普通成员详细信息的账号
     * @return 返回普通成员对应账号的信息
     */
    public Employee getByEmployeeAccount(String account) {
        return employeeDao.getByEmployeeAccount(account);
    }


    /**
     * 查询管理员账号对应的信息
     *
     * @param account 要查询管理员的账号
     * @return 返回对应账号的信息
     */
    public Employee getByManagerAccount(String account) {
        return managerDao.getByManagerAccount(account);
    }


    /**
     * 对前端传来的用户账号跟用户密码进行验证，判断登陆用户的登陆角色
     *
     * @param account  登陆用户的账号
     * @param password 登陆用户的密码
     * @return 返回进行逻辑判断后的验证状态
     */
    public int validLogin(String account, String password) {
        int status;    //返回状态
        if (managerDao.findByNameAndPass(account, password).size() >= 1) {
            status = 2;   //部长
        } else if (employeeDao.findByNameAndPass(account, password).size() >= 1) {
            status = 1;    //普通会员
        } else {
            status = 0;   //登陆失败
        }
        return status;    //返回状态码
    }
}
