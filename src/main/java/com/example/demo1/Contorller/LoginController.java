package com.example.demo1.Contorller;


import com.alibaba.fastjson.JSON;
import com.example.demo1.bean.Account;
import com.example.demo1.service.AccountService;
import com.example.demo1.util.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户登录
 */
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
    public Map login(String name, String pass,String type) {
        if (StringUtils.isBlank(name) || StringUtils.isBlank(pass)||StringUtils.isBlank(type)) {
            return MsgBuilder.buildReturnErrorMessage("用户名或密码不能为空");
        }
        String username = name;
        String password = Md5.MD5(pass);
        Account account = new Account();
        account.setUsername(username);
        account.setStatus(Constant.STATUS_VALID);
        account.setAccount_type(Integer.parseInt(type));
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

}
