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
     * 添加专家账户
     * @param user
     * @return
     */
    public int addSpecia(User user);

    /**
     * 添加用户账号
     * @param user
     * @return
     */
    public int addUser(User user);

    /**
     * 修改医生账户
     * @param user
     * @return
     */
    public int updateDoctor(User user);

    /**
     * 修改专家账户
     * @param user
     * @return
     */
    public int updateSpecia(User user);

    /**
     * 删除专家、医生账号
     * @param id
     * @return
     */
    public int deleteDoctor(int id);

    /**
     * 根据openid查找用户
     * @param user
     * @return
     */
    public User findbyopenid(User user);

    /**
     * 根据uid查找用户
     * @param uid
     * @return
     */
    public List<User> findbyuid(String uid);

    /**
     * 修改
     * @param user
     * @return
     */
    public int updateuser(User user);

    /**
     * 根据pid查询
     * @param pid
     * @return
     */
    public List<User> findbypid(int pid);

    /**
     * 根据姓名手机号查询医生

     * @return
     */
    public List<User> findbynameandphone(User user);
}
