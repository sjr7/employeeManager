package com.suny.service.impl;

import com.suny.dao.impl.AttendDao;
import com.suny.entity.Employee;
import com.suny.utils.Page;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 考勤记录逻辑层
 * 孙建荣
 * 2016/12/13 15:26
 */
@Service
public class AttendService {

    @Autowired
    private AttendDao attendDao;


    /**
     * 通过用户名进行查询考勤数据
     * @param username    要被查询的用户名
     * @param pageCount   每一页显示的记录数
     * @param currentPage    前端页面传过来的当前页页码
     * @return   通过用户名查询到的分页后考勤数据
     */
    public Page<Employee> getByName(String username, int pageCount, int currentPage) {
        Page<Employee> page = new Page<>();                             //实例化page工具类
        Query query = attendDao.getByName(username);                  //得到一个查询的集合
        int totalCount = query.list().size();                      //得到返回集合的大小
        page.setCurrentPage(currentPage);                          //设置当前页页码
        page.setPageCount(pageCount);                             //设置每一页显示的行数
        page.setTotalCount(totalCount);                           //设置查询出来的总记录数
        List attendRecordList = query.list();                    //得到数据集合
        page.setPageDate(attendRecordList);                      //设置分页的数据
        return page;
    }

    /**
     * 获取所有考勤记录信息
     * @param currentPage   前端页面传过来的当前页页码
     * @param pageCount    每页显示的记录数
     * @return    返回分页后的考勤记录
     */
    public Page getAllRecord(int currentPage, int pageCount) {
        Page page = new Page();                                             //实例化page
        int totalCount = attendDao.TotalCount();                            //查询总记录数
        page.setPageCount(pageCount);                                           //设置总页数
        page.setTotalCount(totalCount);                                     //设置总行数
        page.setCurrentPage(currentPage);                                   //设置当前页
        int startCount = page.StartCount(currentPage, pageCount);           //查询当前页开始行数
        List attendRecordList = attendDao.getAllRecord(startCount, pageCount);         //获取分页查询结果集
        page.setPageDate(attendRecordList);                                          //开始设置page相关参数
        return page;
    }

    /**
     *    获取考勤记录总共分页的页数
     * @param pageCount   每一页显示的行数
     * @return    考勤记录分页后的总页数
     */
    public int getMaxPage(int pageCount) {
        int MaxPage;             //最多可以分多少页
        int totalCount = attendDao.TotalCount();    //数据库总页数
        if (totalCount % pageCount == 0) {            //如果最大记录数除以每一页记录数后不存在余数的话，直接是相除
            MaxPage = totalCount / pageCount;
        } else {
            MaxPage = totalCount / pageCount + 1;     //如果有余数则最大页数加1
        }
        return MaxPage;
    }

}






