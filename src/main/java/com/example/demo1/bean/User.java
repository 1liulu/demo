package com.example.demo1.bean;

import java.util.Date;

/**
 * 用户表
 */
public class User {
    private int id;
    //用户编号
    private String uid;
    //手机号
    private String phone;
    //状态
    private int status;
    //医院机构
    private String dept;
    //微信标识
    private String openid;
    //用户信息
    private String name;
    //性别 0男1女
    private int sex;
    //年龄
    private int age;
    //创建时间
    private Date creation_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
