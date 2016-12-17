package com.suny.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 孙建荣
 * 2016/12/11 21:46
 */
@Entity
@Table(name = "application")
public class Application implements Serializable {
    private static final long serialVersionUID = 48L;

    @Id
    @Column(name = "app_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;    //标示属性

    //申请的理由
    @Column(name = "app_reason")
    private String reason;
    //是否处理
    @Column(name = "app_result")
    private boolean result;
    //关联的出勤记录
    @ManyToOne(targetEntity = Attend.class)
    @JoinColumn(name = "attend_id",nullable = false)
    private Attend attend;

    //想要更改的出勤结果
    @ManyToOne(targetEntity = AttendType.class)
    @JoinColumn(name = "type_id",nullable = false)
    private AttendType attendType;

    //申请的结果
    @OneToOne(targetEntity = CheckBack.class,mappedBy = "app")
    private CheckBack checkBack;

    public Application() {
    }

    public Application(String reason, Boolean result, Attend attend, AttendType attendType, CheckBack checkBack) {
        this.reason = reason;
        this.result = result;
        this.attend = attend;
        this.attendType = attendType;
        this.checkBack = checkBack;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Attend getAttend() {
        return attend;
    }

    public void setAttend(Attend attend) {
        this.attend = attend;
    }

    public AttendType getAttendType() {
        return attendType;
    }

    public void setAttendType(AttendType attendType) {
        this.attendType = attendType;
    }

    public CheckBack getCheckBack() {
        return checkBack;
    }

    public void setCheckBack(CheckBack checkBack) {
        this.checkBack = checkBack;
    }
}
