package com.example.demo1.WxContorller;


import com.example.demo1.pay.weixinpay.WeChatPay;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Map;


@RestController
@RequestMapping("/order")
public class payController {


    /**
     * 个人中心
     * @param
     * @return
     */
    @RequestMapping("/user")
    public Map pay(HttpServletRequest request) {
        return null;
    }
}
