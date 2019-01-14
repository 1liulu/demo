package com.example.demo1.Contorller;

import com.example.demo1.bean.Order;
import com.example.demo1.service.OrderService;
import com.example.demo1.util.MsgBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 统计缴费金额
     * @return
     */
    @RequestMapping(value = "/countorder")
    public Map countorder(){
        Map<String, Object> map = new HashMap<>();
        map.put("todayprice",orderService.todayprice());
        map.put("monthprice",orderService.monthprice());
        map.put("countprice",orderService.countprice());
        return MsgBuilder.buildReturnMessage(map);
    }

    /**
     * 查询全部订单
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/page")
    public Map page(String page, String size) {
        Integer pageNum = 1;
        Integer pageSize = 10;
        if (!StringUtils.isBlank(page)) {
            pageNum = Integer.parseInt(page);
        }
        if (!StringUtils.isBlank(size)) {
            pageSize = Integer.parseInt(size);
        }
        PageHelper.startPage(pageNum, pageSize);
        List<Order> orderList=orderService.findall();
        PageInfo<Order> pageInfo = new PageInfo<>(orderList);
        return MsgBuilder.buildReturnMessage(pageInfo);
    }


}
