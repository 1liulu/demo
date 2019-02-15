package com.example.demo1.WxContorller;


import com.alibaba.fastjson.JSON;
import com.example.demo1.bean.Patient;
import com.example.demo1.bean.User;
import com.example.demo1.service.PatientService;
import com.example.demo1.service.UserService;
import com.example.demo1.util.Constant;
import com.example.demo1.util.MsgBuilder;
import com.example.demo1.util.Token;
import com.example.demo1.util.Wechat2;
import com.ksyun.ks3.utils.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/wx")
public class LoginsController {
    @Autowired
    UserService userService;
    @Autowired
    PatientService patientService;
    /**
     * 微信登录
     * @return
     */
    @RequestMapping("/wechatlogin")
    public void wechatlogin(HttpServletRequest request, HttpServletResponse response) {
        StringBuffer  ctxPath =new StringBuffer(request.getScheme() + "://" + request.getServerName() + request.getContextPath());
        ctxPath.append("/code");
        String url =Wechat2.getAuthorityUrl(ctxPath.toString(), "STATE", "snsapi_base");
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @RequestMapping("/code")
    public Map code(String code){
        Map<String, Object> result = new HashMap<>();
        int type;
//        JSONObject json =Wechat2.getReturmParam(Wechat2.getTokenAndOpenidUrl(code));
//        String openid="";
//        try{
//            openid = json.getString("openid");
//        }catch(Exception e){
//            //失败
//            return MsgBuilder.buildReturnErrorMessage("解析异常");
//        }
        String openid=code;
        User user=userService.findbyopenid(openid);
        Map tokenMap = new HashMap(1);
        if (user!=null){
                //存在此用户
                if(user.getPhone()==null||user.getCardnum()==null){
                    //没绑定手机号
                    //进入绑定页面
                    type=1;
                }else{
                    //已绑定手机号
                    //进入个人中心
                    type=0;
                }


        }else{
            //不存在此用户  添加用户
            User user1=new User();
            user1.setOpenid(openid);
            user1.setStatus(Constant.STATUS_VALID);
            user1.setType(Constant.TYPE_USER);
            user1.setCreation_time(new Date());
            userService.addUser(user1);
            //进入绑定页面
            type=1;
            user=user1;
        }
        tokenMap.put("user", user);
        String tokenStr = Token.createToken(JSON.toJSONString(tokenMap));
        if (Constant.GENERATE_TOKEN_ERROR.equals(tokenStr)) {
            return MsgBuilder.buildReturnErrorMessage("token生成异常，请稍候重试！");
        }
        result.put("token", tokenStr);
        result.put("type",type);
        return MsgBuilder.buildReturnMessage(result);
    }

    /**
     * 验证手机号
     * @param phone
     * @return
     */
    @RequestMapping("/phone")
    public Map phone(String phone){
        if (StringUtils.isBlank(phone)) {
            return MsgBuilder.buildReturnErrorMessage("请输入手机号");
        }
        int type;
        Map<String, Object> result = new HashMap<>();
        User user=new User();
        user.setType(Constant.TYPE_USER);
        user.setPhone(phone);
        User user1=userService.finbyphone(user);
        if(user1==null){
            //没有用户绑定手机号
            type=1;

        }else{
            //有用户绑定手机号
            type=0;
        }
        result.put("type",type);
        return MsgBuilder.buildReturnMessage(result);
    }

    /**
     * 验证卡号
     * @param cardnum
     * @return
     */
    @RequestMapping("/cardnum")
    public Map cardnum(String cardnum){
        if (StringUtils.isBlank(cardnum)) {
            return MsgBuilder.buildReturnErrorMessage("请输入卡号");
        }
        //判断卡号是否存在系统中
        Patient patient = new Patient();
        patient.setUid(cardnum);
        patient.setStatus(Constant.STATUS_VALID);
        List<Patient> patientList = patientService.findbyuid(patient);
        if(patientList.size()==0){
            //不在系统中
            return MsgBuilder.buildReturnErrorMessage("输入的卡号不存在");
        }
        //判断是否有用户已绑定卡号
        List<User> userList=userService.findbyuid(cardnum);
        if (userList.size()!=0){
            return MsgBuilder.buildReturnErrorMessage("该卡号已被绑定请解除后绑定");
        }
        return MsgBuilder.buildReturnMessage("卡号验证通过");
    }

    /**
     * 绑定信息
     * @param phone
     * @param cardnum
     * @param httpServletRequest
     * @return
     */
    @RequestMapping("/user")
    public Map user(String phone,String cardnum,HttpServletRequest httpServletRequest){
        if (StringUtils.isBlank(phone)) {
            return MsgBuilder.buildReturnErrorMessage("请输入手机号");
        }
        if (StringUtils.isBlank(cardnum)) {
            return MsgBuilder.buildReturnErrorMessage("请输入卡号");
        }
        User user =  (User) httpServletRequest.getAttribute("user");
        user.setPhone(phone);
        user.setCardnum(cardnum);
        userService.updateuser(user);
        Map tokenMap = new HashMap(1);
        tokenMap.put("user", user);
        String tokenStr = Token.createToken(JSON.toJSONString(tokenMap));
        if (Constant.GENERATE_TOKEN_ERROR.equals(tokenStr)) {
            return MsgBuilder.buildReturnErrorMessage("token生成异常，请稍候重试！");
        }
        return MsgBuilder.buildReturnMessage(tokenStr);
    }
}
