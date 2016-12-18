package com.suny.dao.impl;

import com.suny.entity.Attend;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * 考勤类型数据库操作
 * 孙建荣
 * 2016/12/18 19:43
 */
@Repository
public class AttendTypeDao<T> {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
                return sessionFactory.getCurrentSession();
    }

    /**
     * 保存一个新的考勤记录
     * @param attend   要保存的对象
     */
    public void save(Attend attend){
        getSession().save(attend);
    }

    /**
     * 获取一条对应状态的考勤状态
     * @param clazz   考勤状态类
     * @param id      对应的考勤状态id
     * @return   一条对应考勤状态的对象
     */
    public T get(Class<T> clazz,Serializable id){
        return (T) getSession().get(clazz,id);
    }

}






