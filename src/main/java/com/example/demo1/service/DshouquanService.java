package com.example.demo1.service;

import com.example.demo1.bean.Dshouquan;
import com.example.demo1.mapper.DshouquanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class DshouquanService {
    @Autowired
    DshouquanMapper dshouquanMapper;
    /**
     * 添加授权信息
     * @param dshouquan
     * @return
     */
    public int addshouquan(Dshouquan dshouquan){
        return dshouquanMapper.addshouquan(dshouquan);
    }
}