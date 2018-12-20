package com.example.demo1.service;

import com.example.demo1.bean.Account;
import com.example.demo1.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountMapper accountMapper;
    public List<Account> accountList(Account account){
        return accountMapper.findbyusername(account);
    }
}
