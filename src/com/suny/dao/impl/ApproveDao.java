package com.suny.dao.impl;

import com.suny.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

/**
 * 对考勤有异议的申请数据库操作层
 * 孙建荣
 * 2016/12/13 17:30
 */
@Repository
public class ApproveDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();      //返回session
    }

    /**
     * 保存一个实体类对象
     *
     * @param entity 要保存的实体类对象
     */
    public void save(T entity) {
        getSession().save(entity);    //保存该实体类对象
        getSession().flush();     //刷新缓存
    }


    /**
     * 更新某个实体类对象
     * @param tClass   要被更新的实体类对象
     */
    public void update(T tClass) {
        getSession().update(tClass);     //更新实体类对象
    }


    /**
     * 根据用户名查询处理申请的管理员
     * @param userName   查询的用户名
     * @return     通过某用户名查询出来的管理员信息
     */
    public Employee findByName(String userName) {
        String hql = "select m from Manager as m where m.username = ?0";   //构建通过管理员用户名查询的语句
        Query query = getSession().createQuery(hql);       //构建查询集合
        query.setParameter("0", userName);             //为第一个占位符复制用户名属性
        return (Employee) query.uniqueResult();       //返回唯一性结果
    }


    /**
     *    获取某个实体类对象
     * @param entityClazz     实体类对象
     * @param id      主键标示id
     * @return    返回得到的一个实体类对象
     */
    public T get(Class<T> entityClazz, Serializable id) {
        return (T) getSession().get(entityClazz, id);
    }

    /**
     *    获取所有待审批列表的数据
     * @param startCount    获取数据库数据的起始行
     * @param pageCount     设置要查询的总个数
     * @return    分页查询后的呆审批列表的数据
     */
    public List getApproveList(int startCount, int pageCount) {
        String hql = "from Application as a where a.result = false ";                        //创建hql语句
        Query query = getSession().createQuery(hql);            //创建query查询
        query.setFirstResult(startCount);               //设置查询起始行数
        query.setMaxResults(pageCount);                //设置查询最大结果数
        return query.list();                          //返回集合
    }


    /**
     * 获取申请修改考勤记录总记录数
     * @return    待审批列表的总记录数
     */
    public int TotalApproveListCount() {
        String hql = "select count(*) from Application";           //构建查询申请列表表总记录数
        Query query = getSession().createQuery(hql);           //返回结果集
        return (int) ((long) query.uniqueResult());     //uniqueResult()返回的是一个long类型的数
    }
}
