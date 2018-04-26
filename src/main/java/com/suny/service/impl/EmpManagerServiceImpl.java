package com.suny.service.impl;

import com.suny.dao.impl.AttendTypeDao;
import com.suny.dao.impl.EmployeeDao;
import com.suny.dao.impl.ManagerDao;
import com.suny.entity.Attend;
import com.suny.entity.AttendType;
import com.suny.entity.Employee;
import com.suny.service.EmpManagerService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 成员管理相关的代码
 * 孙建荣
 * 2016/12/12 13:01
 */
@Service
public class EmpManagerServiceImpl implements EmpManagerService {
    @Autowired
    private ManagerDao managerDao;
    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private AttendTypeDao<AttendType> attendTypeDao;

    @Test
    public void autoPunch(){
        System.out.println("自动插入缺勤记录");
        List<Employee> employeeList=managerDao.findAll();    //获取数据库中所有的普通成员
        //获取当前的时间
//        String dutyDay =new java.sql.Date(System.currentTimeMillis()).toString();
        for(Employee employee:employeeList){    //遍历list对象
            //获取旷工对应的考勤类型
            AttendType attendType= attendTypeDao.get(AttendType.class,1);
            Attend attend=new Attend();     //实例化一条考勤记录
            attend.setDutyDay(new Date());     //设置自动添加考勤记录的时间
            attend.setAttendType(attendType);    //设置考勤类型
            attend.setEmployee(employee);      //设置考勤记录对应着的成员
            attendTypeDao.save(attend);       //保存一条考勤记录
        }
    }


    /**
     * 查询普通成员账号对应的信息
     *
     * @param account 要查询普通成员详细信息的账号
     * @return 返回普通成员对应账号的信息
     */
    @Override
    public Employee getByEmployeeAccount(String account) {
        return employeeDao.getByEmployeeAccount(account);
    }


    /**
     * 查询管理员账号对应的信息
     *
     * @param account 要查询管理员的账号
     * @return 返回对应账号的信息
     */
    @Override
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
    @Override
    public int validLogin(String account, String password) {
        int status;    //返回状态
        // TODO 直接进管理员页面
//        if (managerDao.findByNameAndPass(account, password).size() >= 1) {
//            status = 2;   //部长
//        } else if (employeeDao.findByNameAndPass(account, password).size() >= 1) {
//            status = 1;    //普通会员
//        } else {
//            status = 0;   //登陆失败
//        }
//        return status;    //返回状态码
        return 2;
    }
}
