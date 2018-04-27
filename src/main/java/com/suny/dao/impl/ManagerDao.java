package com.suny.dao.impl;

import com.suny.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员相关的数据库操作层
 * 孙建荣
 * 2016/12/12 13:00
 */
@Repository
public class ManagerDao<T> {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();      //返回session
    }

    /**
     * 查询所有普通成员的信息
     * @return   所有普通成员的信息
     */
    public List<Employee> findAll(){
        String hql="from Employee ";
        Query query = getSession().createQuery(hql);
        return query.list();
    }


    /**
     * 查询账号对应的用户信息
     * @param account   查询的账号
     * @return  返回一个对应的用户信息
     */
    public Employee getByAccount(String account) {
        String hql = "from  Manager  where account= ?0 ";         //创建hql查询语句
        Query query=getSession().createQuery(hql);       //创建一个查询
        query.setParameter("0", account);                    //给第一个参数Student_id赋值，值为表单传过来的用户名
        Object uniqueResult = query.uniqueResult();     //返回查询中的唯一结果，对应着账户名不能重复
        return (Employee) uniqueResult;                     //返回查询到的账号信息给service
    }

}





