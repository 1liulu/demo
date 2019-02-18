package com.example.demo1.WxContorller;

import com.example.demo1.bean.OrderDetails;
import com.example.demo1.bean.User;
import com.example.demo1.service.OrderService;
import com.example.demo1.util.MsgBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/Consultation")
public class ConsultationController {
    @Autowired
    OrderService orderService;

    /**
     *专家远程会诊结果
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/list")
    public Map list(HttpServletRequest httpServletRequest){
        User user =  (User) httpServletRequest.getAttribute("user");
        List<OrderDetails> orderDetailsList=orderService.findbyuid(user.getId());
        return MsgBuilder.buildReturnMessage(orderDetailsList);
    }
}
