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
public class ManagerDao {
    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();      //返回session
    }

    /**
     * 查询账号对应的用户信息
     * @param account   查询的账号
     * @return  返回一个对应的用户信息
     */
    public Employee getByManagerAccount(String account) {
        String hql = "from  Manager  where account= ?0 ";         //创建hql查询语句
        Query query=getSession().createQuery(hql);       //创建一个查询
        query.setParameter("0", account);                    //给第一个参数Student_id赋值，值为表单传过来的用户名
        Object uniqueResult = query.uniqueResult();     //返回查询中的唯一结果，对应着账户名不能重复
        return (Employee) uniqueResult;                     //返回查询到的账号信息给service
    }

    /**
     * 根据用户名和密码查询一个部长的信息，是否存在这样的账号跟密码
     * @param account 查询的用户名
     * @param password 查询的密码
     * @return 符合指定用户名和密码的部长资料
     */
    public List findByNameAndPass(String account, String password){
        String hql="select m from Manager as m where m.account = ?0 and m.password  = ?1";    //查询对应部长资料的的hql语句
        Query query=getSession().createQuery(hql);    //构建一个查询集
        query.setParameter("0",account);      //设置第一个参数为账号
        query.setParameter("1",password);    //设置第二个账号为密码
        return query.list();                 //返回查询集查询出来的资料
    }

}





