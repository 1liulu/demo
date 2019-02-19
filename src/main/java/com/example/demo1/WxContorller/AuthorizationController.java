package com.example.demo1.WxContorller;

import com.example.demo1.bean.Dshouquan;
import com.example.demo1.bean.User;
import com.example.demo1.service.DshouquanService;
import com.example.demo1.service.UserService;
import com.example.demo1.util.Constant;
import com.example.demo1.util.MsgBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
    @Autowired
    UserService userService;
    @Autowired
    DshouquanService dshouquanService;
    /**
     * 授权医生
     * @param httpServletRequest
     * @param name
     * @param phone
     * @param date
     * @param reportid
     * @return
     */
    @RequestMapping("/addAuthorization")
    public Map addAuthorization(HttpServletRequest httpServletRequest, String name, String phone, String date, String reportid){
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
        if (userList.size()==0){
            return MsgBuilder.buildReturnErrorMessage("不存在该医生请重新输入");
        }
        User user=userList.get(0);
        User user1 = (User) httpServletRequest.getAttribute("user");
        Dshouquan dshouquan=new Dshouquan();
        dshouquan.setStarttime(new Date());
        dshouquan.setStatus(Constant.STATUS_VALID);
        dshouquan.setUid(user1.getId());
        dshouquan.setRid(Integer.parseInt(reportid));
        dshouquan.setTo_who(user.getId());
        dshouquan.setEndtime(new Date());
        dshouquanService.addshouquan(dshouquan);
        return MsgBuilder.buildReturnMessage("授权成功");
    }
}
