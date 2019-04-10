package com.example.demo1.service;

import com.example.demo1.bean.Order;
import com.example.demo1.bean.OrderDetails;
import com.example.demo1.bean.User;
import com.example.demo1.mapper.OrderMapper;
import com.example.demo1.mapper.UserMapper;
import com.example.demo1.pay.weixinpay.WeChatPay;
import com.example.demo1.util.Constant;
import com.example.demo1.util.MsgBuilder;
import org.jdom.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Service
public class OrderService {
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    UserMapper userMapper;

    /**
     * 当天缴费总额
     *
     * @return
     */
    public double todayprice() {
        return orderMapper.todayprice();
    }

    /**
     * 当月缴费总额
     *
     * @return
     */
    public double monthprice() {
        return orderMapper.monthprice();
    }

    /**
     * 总缴费总额
     *
     * @return
     */
    public double countprice() {
        return orderMapper.countprice();
    }

    /**
     * 查找全部已支付未删除订单
     *
     * @return
     */
    public List<Order> findall() {
        return orderMapper.findall();
    }

    /**
     * 查找用户下订单信息
     *
     * @param id
     * @return
     */
    public List<OrderDetails> findbyuid(int id) {
        return orderMapper.findbyuid(id);
    }

    /**
     * 添加订单
     *
     * @param order
     * @return
     */
    public Map addorder(Order order, String id,HttpServletRequest request)throws JDOMException {
        List<User> userList=userMapper.findbycardnum(id);
        if (userList.size()==0){
            return MsgBuilder.buildReturnErrorMessage("订单失败");
        }
        Date date = new Date(); //获取当前的系统时间。
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss") ; //使用了默认的格式创建了一个日期格式化对象。
        String time = dateFormat.format(date);
        int num=(int)(Math.random()*9000)+1000;
        String str = String.valueOf(num);
        order.setOrder_num(str);
        order.setUid(userList.get(0).getId());
        order.setCreatetime(new Date());
        order.setIs_pay(0);
        order.setStatus(1);
        order.setPrice(1);
        order.setIs_handle(2);
        int i= orderMapper.addorder(order);

        if(i!=0){
            User user=new User();
            user.setId(order.getUid());
            user.setType(Constant.TYPE_USER);
            user=userMapper.findbyid(user);
            String notify_url = request.getScheme() + "://"+request.getServerName()+request.getContextPath()+"/wechat/WeChatOrderSearchApi";
            System.out.println("微信支付回调地址：-----------------"+notify_url+"-----------------");
            Map<String, Object> payMap = new WeChatPay().wechatPayJSAPI(order.getOrder_num(), new BigDecimal(0.1), user.getOpenid(), "支付", request,notify_url);
            if(payMap.get("error")==null){
                payMap.put("orderid",order.getOrder_num());
                payMap.put("id",order.getId());
                return MsgBuilder.buildReturnMessage(payMap);
            }
            return MsgBuilder.buildReturnErrorMessage("订单失败");
        }else{
            return MsgBuilder.buildReturnErrorMessage("订单失败");
        }
    }

    /**
     * 修改订单状态
     * @param id
     * @return
     */
    public int updateorder(int id){
        return orderMapper.updateorder(id);
    }
}
