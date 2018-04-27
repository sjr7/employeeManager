package com.suny.entity;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 孙建荣
 * 2016/12/11 21:36
 */
@Entity
@Table(name = "attend_type")
public class AttendType implements Serializable{
    private static final long serialVersionUID = 48L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Integer id;    //标示属性

    @Column(name = "type_name",nullable = false,length = 50)
    private String name;

    public AttendType(Integer id,String name) {
        this.name = name;
        this.id=id;
    }

    public AttendType() {
    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "AttendType{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}






