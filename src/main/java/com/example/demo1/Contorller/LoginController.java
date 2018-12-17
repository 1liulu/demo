package com.example.demo1.Contorller;


import com.example.demo1.bean.User;
import com.example.demo1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    //登录
    @RequestMapping(value="/login"/*,method = RequestMethod.GET*/)
    public User login(String name,String pass){
        System.out.println("登录");
        User user=new User();
        user.setU_name(name);
        user.setU_password(pass);
        return userMapper.selectuser(user);
    }
    //进入首页
    @RequestMapping(value = "/index")
    public String index() {
        System.out.println("首页");
        return "../index.html";
    }
}
