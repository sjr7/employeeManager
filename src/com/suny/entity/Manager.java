package com.suny.entity;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 孙建荣
 * 2016/12/11 20:31
 * 部长实体类
 */
@Entity
@DiscriminatorValue(value = "2")
public class Manager extends Employee implements Serializable {

    private static final long serialVersionUID = 48L;


    //该部长可以管理的部门
    @Column(name="dept_name",length = 50)
    private String dept;

    //该部长管理的成员
    @OneToMany(targetEntity = Employee.class,mappedBy = "manager")
    private Set<Employee> employees=new HashSet<>();

    //该部长审批的所有批复
    @OneToMany(targetEntity = CheckBack.class,mappedBy = "manager")
    private Set<CheckBack> checkBacks=new HashSet<>();

    public Manager() {
    }

    public Manager(Set<CheckBack> checkBacks, String dept, Set<Employee> employees) {
        this.checkBacks = checkBacks;
        this.dept = dept;
        this.employees = employees;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }

    public Set<CheckBack> getCheckBacks() {
        return checkBacks;
    }

    public void setCheckBacks(Set<CheckBack> checkBacks) {
        this.checkBacks = checkBacks;
    }
}




