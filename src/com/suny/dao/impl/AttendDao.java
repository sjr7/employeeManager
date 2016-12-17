package com.suny.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 查询考勤记录数据库操作
 * 孙建荣
 * 2016/12/13 15:30
 */
@Repository
public class AttendDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();      //返回session
    }


    /**
     * 通过学生姓名进行查询考勤记录
     *
     * @param username 要查询考勤记录的用户名
     * @return 返回通过用户名进行查询的查询集
     */
    public Query getByName(String username) {
        String hql = "from Attend where employee.username like ?0";      //构建通过名字查询
        Query query = getSession().createQuery(hql);        //构建集合
        query.setParameter("0", "%" + username + "%");          //设置参数，hibernate中模糊查询拼接%号
        return query;
    }

    /**
     * 获取所有的考勤数据
     *
     * @param startCount 查询考勤数据的起始行
     * @param pageCount  吗每一页显示的个数
     * @return 分页后的考勤数据
     */
    public List getAllRecord(int startCount, int pageCount) {
        String hql = "from Attend";                        //创建hql语句
        Query query = getSession().createQuery(hql);            //创建query查询
        query.setFirstResult(startCount);               //设置查询起始行数
        query.setMaxResults(pageCount);                //设置查询最大结果数
        return query.list();                          //返回集合
    }


    /**
     * 获取考勤记录数据库总记录数
     *
     * @return 数据库中考勤记录的总记录数
     */
    public int TotalCount() {
        String hql = "select count(*) from Attend";           //构建查询Student表总记录数
        Query query = getSession().createQuery(hql);           //返回结果集
        return (int) ((long) query.uniqueResult());     //uniqueResult()返回的是一个long类型的数
    }


}






