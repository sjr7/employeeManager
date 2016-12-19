package com.suny.service.impl;

import com.suny.dao.impl.AttendDao;
import com.suny.dao.impl.AttendTypeDao;
import com.suny.dao.impl.EmployeeDao;
import com.suny.entity.Attend;
import com.suny.entity.AttendType;
import com.suny.entity.Employee;
import com.suny.utils.Page;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * employeeService实现类
 * 孙建荣
 * 2016/11/11 13:44
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeDao employeeDao;     //注入employeeDao

    @Autowired
    private AttendDao attendDao;

    @Autowired
    private AttendTypeDao attendTypeDao;


    public int punch(String username){
        Employee employee = employeeDao.findByEmpName(username);
        if (employee == null) {
             return  0;   //失败
        }
        String nowDate = new java.sql.Date(System.currentTimeMillis()).toString();   // 生成一个当前日期
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dutyDay = null;
        try {
            dutyDay = simpleDateFormat.parse(nowDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Attend attend = (Attend) attendDao.findByEmployeeDutyDay(employee, dutyDay);
        if(attend == null  ){
            System.out.println("现在还不能签到");
            return 1;    //现在还不能签到
        }
        if(attend.getPunchTime() != null){   //如果已经有了签到时间
            System.out.println("已经签到过了");
            return 2;
        }
        else{

            //接下来就是可以开始签到的时候了
            int punchTime= Calendar.getInstance().get(Calendar.HOUR_OF_DAY);   // 获取签到的时间,用来判断各种考勤情况
            attend.setPunchTime(new Date());
            //这里就不对签到时间进行各种考勤情况的判定了，要么是正常，要么是缺勤
            attend.setAttendType((AttendType) attendTypeDao.get(AttendType.class,5));
            attendDao.update(attend);
            return 3;    //签到成功
        }

    }

    /**
     * 根据用户名查询是否可以签到
     *
     * @param username 想签到的用户名
     * @return 是否可以签到的状态码
     * @throws Exception
     */
    public int validPunch(String username) throws Exception {
        int status = 0;
        String nowDate = new java.sql.Date(System.currentTimeMillis()).toString();   // 生成一个当前日期
        DateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date dutyDay = simpleDateFormat.parse(nowDate);
        Employee employee = employeeDao.findByEmpName(username);                  //根据用户名查找这个用户
        if (employee == null) {
            System.out.println("根本就没这个用户");
            status = 0;
        }
        List attendList = attendDao.findByEmployeeDutyDay(employee, dutyDay);     //用今天的日期跟员工对象去查找今天的缺勤记录
        if (attendList == null || attendList.size() <= 0) {
            System.out.println("还不能签到现在");
            status = 0;             //返回的状态码
        } else if (!(attendList == null)) {
            System.out.println("数据库有签到记录，可以签到");
            status = 1;               //返回的状态码
        }
        return status;
    }


    /**
     * 发送修改密码请求
     *
     * @param username    要修改密码的用户名
     * @param oldPassword 老密码
     * @param newPassword 新密码
     */
    public void checkPassword(String username, String oldPassword, String newPassword) {
        Employee employee = employeeDao.findByEmpName(username);
        if (employee == null) {
            System.out.println("用户不存在，恶意修改密码");
        } else if (employee.getPassword().equals(oldPassword)) {
            employee.setPassword(newPassword);
            employeeDao.update(employee);
        }
    }

    /**
     * 修改成员数据
     *
     * @param employee 要修改数据的employee对象
     * @param id       要修改数据的employee的主键标示id
     */
    public void modifyOperation(Employee employee, Integer id) {
        employeeDao.modifyOperation(employee, id);     //向employeeDao传递要修改的成员数据跟id
    }

    /**
     * 增加成员信息
     *
     * @param employee 要增加的成员信息对象
     */
    public void addOperation(Employee employee) {
        employeeDao.addOperation(employee);    //向employeeDao传递要增加的成员数据
    }

    /**
     * 获取所有成员信息
     *
     * @param currentPage 前端传来的当前页页码
     * @param pageCount   每一页显示的记录数
     * @return 分页后的数据
     */
    public Page getAll(int currentPage, int pageCount) {
        Page page = new Page();                                             //实例化page
        int totalCount = employeeDao.TotalCount();                            //查询总记录数
        page.setPageCount(pageCount);                                           //设置总页数
        page.setTotalCount(totalCount);                                     //设置总行数
        page.setCurrentPage(currentPage);                                   //设置当前页
        int startCount = page.StartCount(currentPage, pageCount);           //查询当前页开始行数
        List employeeList = employeeDao.getAll(startCount, pageCount);         //获取分页查询结果集
        page.setPageDate(employeeList);                                          //开始设置page相关参数
        return page;
    }

    /**
     * 通过用户名查询
     *
     * @param username    要被查询的用户名
     * @param pageCount   每一页显示的记录数
     * @param currentPage 前端传来的当前页页码
     * @return 通过用户名查询的记录，然后再进行分页后的数据
     */
    public Page getByName(String username, int pageCount, int currentPage) {
        Page<Employee> page = new Page<>();                             //实例化page工具类
        Query query = employeeDao.getByName(username);                  //得到一个查询的集合
        int totalCount = query.list().size();                      //得到返回集合的大小
        page.setCurrentPage(currentPage);                          //设置当前页页码
        page.setPageCount(pageCount);                             //设置每一页显示的行数
        page.setTotalCount(totalCount);                           //设置查询出来的总记录数
        List employeeList = query.list();                    //得到数据集合
        page.setPageDate(employeeList);                      //设置分页的数据
        return page;
    }


    /**
     * 通过id获取单个成员信息
     *
     * @param id 要获取成员信息的主键标识id
     * @return 通过该id查询出来的employee对象
     */
    public Employee getById(Integer id) {
        return employeeDao.getById(id);
    }

    /**
     * 删除一个成员信息
     *
     * @param id 要删除的成员的主键标识id
     */
    public void deleteOperation(Integer id) {
        employeeDao.deleteOperation(id);      //给dao层传递删除的成员id
    }


    /**
     * 获取总共分页的页数
     *
     * @param pageCount 每一页显示的记录数
     * @return employee表中的最大可以分页数
     */
    public int getMaxPage(int pageCount) {
        int MaxPage;
        int totalCount = employeeDao.TotalCount();    //数据库总页数
        if (totalCount % pageCount == 0) {
            MaxPage = totalCount / pageCount;    //数据库中总记录数除以每一页显示的记录数。除的尽就直接是最大页
        } else {
            MaxPage = totalCount / pageCount + 1;    //除不尽就是要增加一页
        }
        return MaxPage;
    }


}
