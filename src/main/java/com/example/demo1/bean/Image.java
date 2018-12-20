package com.example.demo1.bean;

import java.util.Date;

/**
 * 检查报告图片
 */
public class Image {
    //本系统主键ID
    private int id;
    //关联系统中病人检查报告唯一id
    private String report_id;
    //图片地址
    private String path;
    //状态
    private int status;
    //创建时间
    private Date creation_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }
}
