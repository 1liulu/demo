package com.example.demo1.mapper;


import com.example.demo1.bean.Order;
import com.example.demo1.bean.OrderDetails;

import java.util.List;

public interface OrderMapper {
    /**
     * 当天缴费总额
     * @return
     */
    public double todayprice();

    /**
     * 当月缴费总额
     * @return
     */
    public double monthprice();

    /**
     * 总缴费总额
     * @return
     */
    public double countprice();

    /**
     * 查找全部已支付未删除订单
     * @return
     */
    public List<Order> findall();

    /**
     * 查找用户下订单信息
     * @param id
     * @return
     */
    public List<OrderDetails> findbyuid(int id);
}
