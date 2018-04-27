package com.suny.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 孙建荣
 * 2016/12/11 20:46
 */
@Entity
@Table(name = "employee")
@DiscriminatorColumn(name = "emp_type",discriminatorType =DiscriminatorType.INTEGER )
@DiscriminatorValue(value = "1")
public class Employee implements Serializable{
    private static final long serialVersionUID = 48L;

    //标示属性
    @Id
    @Column(name = "emp_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "emp_account",nullable=false, length=50 , unique=true)      //登陆账号
    private String account;

    @Column(name = "emp_password", nullable=false, length=50)    //登录密码
    private String password;

    @Column(name = "emp_name")
    private String username;    //姓名

    @Column(name = "emp_className")
    private String className;    //班级



    @Column(name = "emp_tel")
    private String tel;         //个人电话号码

    @Column(name = "emp_bedroom")
    private String bedroom;    //宿舍

    //成员对应的部长
    @ManyToOne(targetEntity = Manager.class)
    @JoinColumn(name="mgr_id")
    private  Manager manager;
    //成员对应的签到记录
    @OneToMany(targetEntity = Attend.class,mappedBy = "employee")
    private Set<Attend> attends=new HashSet<>();

    public Employee() {
    }

    public Employee(String account, String password, String username, String className, String tel, String bedroom) {
        this.account = account;
        this.password = password;
        this.username = username;
        this.className = className;
        this.tel = tel;
        this.bedroom = bedroom;
//        this.manager = manager;
//        this.attends = attends;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }



    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }


    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getBedroom() {
        return bedroom;
    }

    public void setBedroom(String bedroom) {
        this.bedroom = bedroom;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public Set<Attend> getAttends() {
        return attends;
    }

    public void setAttends(Set<Attend> attends) {
        this.attends = attends;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", className='" + className + '\'' +
                ", tel='" + tel + '\'' +
                ", bedroom='" + bedroom + '\'' +
                ", attends=" + attends +
                '}';
    }
}
