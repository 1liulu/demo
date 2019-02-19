package com.example.demo1.service;

import com.example.demo1.bean.Dshouquan;
import com.example.demo1.bean.User;
import com.example.demo1.mapper.DshouquanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


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
    /**
     * 根据用户id查找
     * @param uid
     * @return
     */
    public List<Dshouquan> findbyuid(int uid,int type){
        User user=new User();
        user.setId(uid);
        user.setType(type);
        return dshouquanMapper.findbyuid(user);
    }
}
