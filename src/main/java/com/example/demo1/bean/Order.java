package com.example.demo1.bean;


import java.util.Date;

public class Order {
    private int id;
    private int uid;
    private int did;
    private String order_num;
    private int is_pay;
    private int status;
    private double price;
    private Date createtime;
    private int film_num;
    private Date pay_time;
    private int is_handle;

    public int getFilm_num() {
        return film_num;
    }

    public void setFilm_num(int film_num) {
        this.film_num = film_num;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public int getIs_handle() {
        return is_handle;
    }

    public void setIs_handle(int is_handle) {
        this.is_handle = is_handle;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getOrder_num() {
        return order_num;
    }

    public void setOrder_num(String order_num) {
        this.order_num = order_num;
    }

    public int getIs_pay() {
        return is_pay;
    }

    public void setIs_pay(int is_pay) {
        this.is_pay = is_pay;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}
