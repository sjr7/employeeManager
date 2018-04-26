package com.suny.entity;


import javax.persistence.*;
import java.io.Serializable;

/**
 * 孙建荣
 * 2016/12/11 20:38
 */
@Entity
@Table(name = "check_back")
public class CheckBack implements Serializable {
    private static final long serialVersionUID = 48L;

    @Id
    @Column(name = "check_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //是否同意申请
    @Column(name = "check_result", nullable = false)
    private boolean result;

    //批复理由
    @Column(name = "check_reason",length = 255)
    private String reason;

    //批复对应的申请
    @OneToOne(targetEntity = Application.class)
    @JoinColumn(name = "app_id")
    private Application app;

    //审批的部长
    @ManyToOne(targetEntity = Manager.class)
    @JoinColumn(name="mgr_id",nullable = false)
    private Manager manager;

    public CheckBack() {
    }

    public CheckBack(Manager manager, boolean result, String reason, Application app) {
        this.manager = manager;
        this.result = result;
        this.reason = reason;
        this.app = app;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Application getApp() {
        return app;
    }

    public void setApp(Application app) {
        this.app = app;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }


    @Override
    public String toString() {
        return "CheckBack{" +
                "id=" + id +
                ", result=" + result +
                ", reason='" + reason + '\'' +
                ", app=" + app +
                ", manager=" + manager +
                '}';
    }
}





