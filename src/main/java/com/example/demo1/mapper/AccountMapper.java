package com.example.demo1.mapper;

import com.example.demo1.bean.Account;
import com.example.demo1.bean.User;

import java.util.List;

public interface AccountMapper {
    /**
     * 根据用户名查找用户
     * @param account
     * @return
     */
    public List<Account> findbyusername(Account account);
}
