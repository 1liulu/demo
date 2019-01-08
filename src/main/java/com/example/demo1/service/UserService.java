package com.example.demo1.service;

import com.example.demo1.bean.User;
import com.example.demo1.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    /**
     * 查询今天新增用户
     * @return
     */
    public int todaycountuser(){
        return userMapper.todaycountuser();
    }

    /**
     * 查询总人数
     * @return
     */
    public int countuser(){
        return userMapper.countuser();
    }

    /**
     * 查询用户
     * @param user
     * @return
     */
    public List<User> finall(User user){
        return userMapper.finall(user);
    }
}
