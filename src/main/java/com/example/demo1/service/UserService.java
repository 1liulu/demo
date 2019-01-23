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

    /**
     * 查询用户

     * @return
     */
    public User finbyid(User user){
        return userMapper.findbyid(user);
    }

    /**
     * 查找手机号是否存在  id！=0时查找除id=？有没有手机号存在
     * @param user
     * @return
     */
    public User finbyphone(User user){
        return userMapper.finbyphone(user);
    }

    /**
     * 添加医生账户
     * @param user
     * @return
     */
    public int addDoctor(User user){
        return userMapper.addDoctor(user);
    }

    /**
     * 添加医生账户
     * @param user
     * @return
     */
    public int addSpecia(User user) {
        return userMapper.addSpecia(user);
    }

    /**
     * 修改医生账户
     * @param user
     * @return
     */
    public int updateDoctor(User user){
        return userMapper.updateDoctor(user);
    }

    /**
     * 修改专家账户
     * @param user
     * @return
     */
    public int updateSpecia(User user) {
        return userMapper.updateSpecia(user);
    }

    /**
     * 删除账号
     * @param id
     * @return
     */
    public int deleteDoctor(int id){
        return userMapper.deleteDoctor(id);
    }
}