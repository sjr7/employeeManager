package com.suny.dao.impl;

import com.suny.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文件操作处理数据库操作层
 * 孙建荣
 * 2016/11/14 12:50
 */
@Repository
public class FileSourceDao  {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * 获取所有的成员数据
     *
     * @return   所有成员的信息
     */
    public List getAllStudentDetails() {
        String hql = "from Employee";              //创建hql语句
        Query query = getSession().createQuery(hql);      //创建query查询
        return query.list();
    }

    /**
     * 批量保存成员信息列表
     * @param employeeList    成员信息集合
     */
    public void saveBatch(List<Employee> employeeList) {
        for (int i = 0; i < employeeList.size(); i++) {
            getSession().save(employeeList.get(i));     //保存每一个employee对象
            System.out.println("开始插入第" + i + "条数据");
        }
    }



}
