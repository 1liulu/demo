package com.example.demo1.Contorller;

import com.example.demo1.bean.Order;
import com.example.demo1.service.OrderService;
import com.example.demo1.util.MsgBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/order")
@Api(tags = "订单")
public class OrderController {
    @Autowired
    OrderService orderService;

    /**
     * 统计缴费金额
     * @return
     */
    @ApiOperation(value = "统计缴费金额", notes = "返回提示")
    @RequestMapping(value = "/countorder", method = {RequestMethod.POST, RequestMethod.GET})
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
    @ApiOperation(value = "查询全部订单", notes = "返回提示")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "page", value = "当前页数", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "size", value = "每页显示数据", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/page", method = {RequestMethod.POST, RequestMethod.GET})
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
