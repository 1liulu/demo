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

    /**
     * 查询用户详情
     * @return
     */
    public User findbyid(User user);

    /**
     * 查找手机号是否存在
     * @param user
     * @return
     */
    public User finbyphone(User user);

    /**
     * 添加医生账户
     * @param user
     * @return
     */
    public int addDoctor(User user);

    /**
     * 修改医生账户
     * @param user
     * @return
     */
    public int updateDoctor(User user);

    /**
     * 删除账号
     * @param id
     * @return
     */
    public int deleteDoctor(int id);
}
