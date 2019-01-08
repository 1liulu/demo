package com.example.demo1.mapper;


import com.example.demo1.bean.User;

import java.util.List;

public interface UserMapper {
    /**
     * 查询今天新增用户
     * @return
     */
    public int todaycountuser();

    /**
     * 查询总人数
     * @return
     */
    public int countuser();

    /**
     * 查询用户
     * @param user
     * @return
     */
    public List<User> finall(User user);
}
