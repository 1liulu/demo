package com.example.demo1.service;

import com.example.demo1.bean.Order;
import com.example.demo1.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;

    /**
     * 当天缴费总额
     * @return
     */
    public double todayprice(){
        return orderMapper.todayprice();
    }

    /**
     * 当月缴费总额
     * @return
     */
    public double monthprice(){
        return orderMapper.monthprice();
    }

    /**
     * 总缴费总额
     * @return
     */
    public double countprice(){
        return orderMapper.countprice();
    }

    /**
     * 查找全部已支付未删除订单
     * @return
     */
    public List<Order> findall(){
        return orderMapper.findall();
    }
}
