package com.suny.dao.impl;

import com.suny.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 普通员工的数据库操作层
 * 孙建荣
 * 2016/11/11 13:42
 */
@Repository
public class EmployeeDao<T>{

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return sessionFactory.getCurrentSession();      //返回session
    }




    /**
     *     保存需要进行持久化的实体类对象
     * @param employee  需要进行保存的实体类对象
     */
    public void addOperation(Employee employee) {
        getSession().save(employee);
    }


    /**
     *    对需要进行修改的实体类对象进行修改
     * @param employee    需要被修改的对象
     * @param id     主键标示id
     */
    public void modifyOperation(Employee employee, Integer id) {
        Employee employee1 = (Employee) getSession().get(Employee.class, id);    //获取要更改的对象类
        employee1.setBedroom(employee.getBedroom());                   //设置寝室号
        employee1.setClassName(employee.getClassName());           //设置班级名字
        employee1.setUsername(employee.getUsername());         //设置姓名
        employee1.setTel(employee.getTel());           //设置电话号码
        getSession().update(employee1);           //更新数据
        getSession().flush();          //刷新session

    }

    /**
     *     删除一个成员的操作
     * @param id   主键标示id，每个id对应着一个用户
     */
    @Transactional
    public void deleteOperation(Integer id) {
        String hql = "delete Employee where id= ?0";     //构建删除学生信息列表中某一的语句
        Query query = getSession().createQuery(hql);     //构建Query
        query.setParameter("0", id);                    //设置参数
        query.executeUpdate();                           //执行更改
    }

    /**
     *   对一个对象进行更新操作
     * @param clazz    需要被更新的实体类
     */
    public void update(T clazz){
        getSession().update(clazz);    //更新要被修改的实体类对象
        getSession().flush();        //对一个回话进行刷新
    }

    /**
     *   对用户的密码进行修改
     * @param id    要修改密码的用户的主键标示id
     * @param password    需要被更新的密码
     */
    public void  changePassword(Integer id,String password) {
        String hql = "update Employee set password =?0 where id=?1";      //构建通过名字查询
        Query query = getSession().createQuery(hql);        //构建集合
        query.setParameter("0",password);   //传入密码
        query.setParameter("1",id );          //设置参数
        query.executeUpdate();            //执行更新
        getSession().flush();         //刷新缓存
    }


    /**
     *    通过学生姓名进行查询
     * @param username     要被查询的用户名对象
     * @return    一个employee对象，对应的是一个用户的信息
     */
    public Employee findByEmpName(String username) {
        String hql = "from Employee where username= ?0";      //构建通过名字查询
        Query query = getSession().createQuery(hql);        //构建集合
        query.setParameter("0",username );          //设置参数
        return (Employee) query.uniqueResult();      //返回查询用户名的唯一结果
    }

    /**
     *    模糊查询用户名
     * @param username    要被查询的用户名
     * @return      返回模糊查询出来的一个集合对象
     */
    public Query getByName(String username) {
        String hql = "from Employee where username like ?0";      //构建通过名字进行模糊查询
        Query query = getSession().createQuery(hql);        //构建集合
        query.setParameter("0", "%" + username + "%");          //设置参数
        return query;                                       //返回查询集合给service进行判断
    }

    /**
     *    对数据库进行查询全部操作
     * @param startCount    要查询的数据库资料的起始行
     * @param pageCount      要查询数据库资料的总个数
     * @return            查询出来的数据分页后的结果集
     */
    public List getAll(int startCount, int pageCount) {
        String hql = "from Employee";                        //创建hql语句
        Query query = getSession().createQuery(hql);            //创建query查询
        query.setFirstResult(startCount);               //设置查询起始行数
        query.setMaxResults(pageCount);                //设置查询最大结果数
        return query.list();                          //返回集合
    }

    /**
     *      查询某个数据库表中总行数
     * @return   返回employee数据库表中的数据库总行数
     */
    public int TotalCount() {
        String hql = "select count(*) from Employee";           //构建查询Student表总记录数
        Query query = getSession().createQuery(hql);           //返回结果集
        return (int) ((long) query.uniqueResult());     //uniqueResult()返回的是一个long类型的数
    }

    /**
     *   通过一个id进行查询一个度一箱
     * @param id   要被查询的一个对象的主键标示id
     * @return     通过该主键标示id查询出来的employee对象
     */
    @Transactional
    public Employee getById(Integer id) {
        String hql = "from Employee  where id= ?0 ";         //创建hql查询语句
        Query query = getSession().createQuery(hql);       //创建一个查询
        query.setParameter("0", id);                    //给第一个参数id赋值，值为表单传过来的用户名
        Object uniqueResult = query.uniqueResult();     //返回查询中的唯一结果，对应着账户名不能重复
       return (Employee) uniqueResult;                     //返回查询到的账号信息给service
    }


    /**
     *  往数据库employee表中批量插入用户信息
     * @param employeeList     传过来的一个employee对象集合
     */
    public void saveBatch(List<Employee> employeeList) {
        for (int i = 0; i < employeeList.size(); i++) {     //对传过来的employee集合进行循环遍历操作
            getSession().save(employeeList.get(i));             //批量插入数据
            System.out.println("开始插入第" + i + "条数据");     //在控制台输出插入的用户数据信息
        }
    }

    /**
     * 根据用户名和密码查询账号是否存在
     * @param account   查询的账号
     * @param password    查询的密码
     * @return   符合账号密码的成员资料
     */
    public List<Employee> findByNameAndPass(String account, String password) {
        String hql="select e from Employee as e where e.account = ?0 and e.password  = ?1";   //构建查询用户账号密码是否匹配语句
        Query query=getSession().createQuery(hql);       //构建查询集合
        query.setParameter("0",account);      //第一个参数赋值为账号
        query.setParameter("1",password);    //第二个参数赋值为密码
        return query.list();                //返回查询集合的结果集
    }

    /**
     * 查询普通成员账号对应的用户信息
     * @param account   查询的账号
     * @return  返回一个对应的用户信息
     */
    public Employee getByEmployeeAccount(String account) {
        String hql = "from  Employee where account= ?0 ";         //创建hql查询语句
        Query query=getSession().createQuery(hql);       //创建一个查询
        query.setParameter("0", account);                    //给第一个参数Student_id赋值，值为表单传过来的用户名
        Object uniqueResult = query.uniqueResult();     //返回查询中的唯一结果，对应着账户名不能重复
        return (Employee) uniqueResult;                     //返回查询到的一个唯一账号信息给service
    }
}
