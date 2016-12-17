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
    private String dutyDay;
    //签到时间
    @Column(name = "punch_time")
    private Date punchTime;
    //是否在规定时间签到
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

    public Attend(String dutyDay, Date punchTime, boolean isCome, AttendType attendType, Employee employee) {
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

    public String getDutyDay() {
        return dutyDay;
    }

    public void setDutyDay(String dutyDay) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Attend attend = (Attend) o;

        if (isCome != attend.isCome) return false;
        if (attendType != null ? !attendType.equals(attend.attendType) : attend.attendType != null) return false;
        if (dutyDay != null ? !dutyDay.equals(attend.dutyDay) : attend.dutyDay != null) return false;
        if (employee != null ? !employee.equals(attend.employee) : attend.employee != null) return false;
        if (id != null ? !id.equals(attend.id) : attend.id != null) return false;
        if (punchTime != null ? !punchTime.equals(attend.punchTime) : attend.punchTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (dutyDay != null ? dutyDay.hashCode() : 0);
        result = 31 * result + (punchTime != null ? punchTime.hashCode() : 0);
        result = 31 * result + (isCome ? 1 : 0);
        result = 31 * result + (attendType != null ? attendType.hashCode() : 0);
        result = 31 * result + (employee != null ? employee.hashCode() : 0);
        return result;
    }
}









