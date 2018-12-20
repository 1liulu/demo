package com.example.demo1.Contorller;


import com.alibaba.fastjson.JSON;
import com.example.demo1.bean.Account;
import com.example.demo1.service.AccountService;
import com.example.demo1.util.*;
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
public class LoginController {
    @Autowired
    private AccountService accountService;

    /**
     * 登录
     *
     * @param name
     * @param pass
     * @return
     */
    @RequestMapping(value = "/login")
    public Map login(String name, String pass) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(pass)) {
            return MsgBuilder.buildReturnErrorMessage("用户名或密码不能为空");
        }
        String username = name;
        String password = Md5.MD5(pass);
        Account account = new Account();
        account.setUsername(username);
        List<Account> accountList = accountService.accountList(account);
        if (null == accountList || accountList.size() != 1) {
            return MsgBuilder.buildReturnErrorMessage("用户不存在");
        }
        Account account1 = accountList.get(0);
        if (!password.trim().equals(account1.getPassword())) {
            return MsgBuilder.buildReturnErrorMessage("密码不正确");
        }
        Map tokenMap = new HashMap(1);
        tokenMap.put("account", account1);
        String tokenStr = Token.createToken(JSON.toJSONString(tokenMap));
        if (Constant.GENERATE_TOKEN_ERROR.equals(tokenStr)) {
            return MsgBuilder.buildReturnErrorMessage("token生成异常，请稍候重试！");
        }
        Map rtnMap = new HashMap();
        rtnMap.put("token", tokenStr);
        rtnMap.put("account", new HashMap(2) {{
            this.put("type", account1.getType());
        }});
        return MsgBuilder.buildReturnMessage(rtnMap);
    }

//    @RequestMapping(value = "/l")
//    public Map l() {
//        //设置分页信息，分别是当前页数和每页显示的总记录数【记住：必须在mapper接口中的方法执行之前设置该分页信息】
//        PageHelper.startPage(1, 10);
//        Account account = new Account();
//        account.setUsername("1");
//        List<Account> accountList = accountService.accountList(account);
//
//        PageInfo<Account> pageInfo = new PageInfo<Account>(accountList);
//
//
//        return MsgBuilder.buildReturnMessage(pageInfo);
//    }


}
