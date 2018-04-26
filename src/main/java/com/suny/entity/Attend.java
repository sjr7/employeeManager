package com.suny.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 孙建荣
 * 2016/12/11 21:00
 */
@Entity
@Table(name = "attend")
public class Attend implements Serializable{
    private static final long serialVersionUID = 48L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attend_id")
    private Integer id;    //标示对象

    //考勤日期
    @Column(name = "duty_day",nullable = false,length =50 )
    private Date dutyDay;

    //签到时间
    @Column(name = "punch_time")
    private java.util.Date punchTime;

    //是否在规定时间签到 0为没有 1为签到
    @Column(name = "is_come")
    private boolean isCome;

    //本次考勤的类型
    @ManyToOne(targetEntity = AttendType.class)
    @JoinColumn(name = "type_id",nullable = false)
    private AttendType attendType;


    //本次考勤的员工
    @ManyToOne(targetEntity = Employee.class)
    @JoinColumn(name = "emp_id")
    private Employee employee;

    public Attend() {
    }

    public Attend(Date dutyDay, Date punchTime, boolean isCome, AttendType attendType, Employee employee) {
        this.dutyDay = dutyDay;
        this.punchTime = punchTime;
        this.isCome = isCome;
        this.attendType = attendType;
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDutyDay() {
        return dutyDay;
    }

    public void setDutyDay(Date dutyDay) {
        this.dutyDay = dutyDay;
    }

    public Date getPunchTime() {
        return punchTime;
    }

    public void setPunchTime(Date punchTime) {
        this.punchTime = punchTime;
    }

    public boolean isCome() {
        return isCome;
    }

    public void setCome(boolean isCome) {
        this.isCome = isCome;
    }

    public AttendType getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendType attendType) {
        this.attendType = attendType;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return "Attend{" +
                "id=" + id +
                ", dutyDay=" + dutyDay +
                ", punchTime=" + punchTime +
                ", isCome=" + isCome +
                ", attendType=" + attendType +
                ", employee=" + employee +
                '}';
    }
}









