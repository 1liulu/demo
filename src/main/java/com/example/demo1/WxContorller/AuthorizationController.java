package com.example.demo1.WxContorller;

import com.example.demo1.bean.User;
import com.example.demo1.service.UserService;
import com.example.demo1.util.MsgBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
    @Autowired
    UserService userService;
    @RequestMapping("/addAuthorization")
    public Map addAuthorization(String name,String phone,String date,String reportid){
        if (StringUtils.isBlank(phone)) {
            return MsgBuilder.buildReturnErrorMessage("请输入手机号");
        }
        if (StringUtils.isBlank(name)) {
            return MsgBuilder.buildReturnErrorMessage("请输入姓名");
        }
        if (StringUtils.isBlank(reportid)) {
            return MsgBuilder.buildReturnErrorMessage("请选择检查报告");
        }
        if (StringUtils.isBlank(date)) {
            return MsgBuilder.buildReturnErrorMessage("请输入日期");
        }
        List<User> userList=userService.findbynameandphone(name,phone);
        return MsgBuilder.buildReturnMessage("");
    }
}
