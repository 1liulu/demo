package com.example.demo1.bean;

import java.util.Date;

public class Dshouquan {
    private int id;
    //报告id
    private int rid;
    //被授权医生
    private int to_who;
    //授权人
    private int uid;
    private int status;
    //开始日期
    private Date starttime;
    //结束日期
    private Date endtime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRid() {
        return rid;
    }

    public void setRid(int rid) {
        this.rid = rid;
    }

    public int getTo_who() {
        return to_who;
    }

    public void setTo_who(int to_who) {
        this.to_who = to_who;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getStarttime() {
        return starttime;
    }

    public void setStarttime(Date starttime) {
        this.starttime = starttime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
