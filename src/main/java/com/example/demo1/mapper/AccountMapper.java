package com.example.demo1.mapper;

import com.example.demo1.bean.Account;

import java.util.List;

public interface AccountMapper {
    /**
     * 根据用户名查找用户
     * @param account
     * @return
     */
    public List<Account> findbyusername(Account account);
}
