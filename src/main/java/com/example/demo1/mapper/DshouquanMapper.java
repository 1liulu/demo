package com.example.demo1.mapper;


import com.example.demo1.bean.Dshouquan;
import com.example.demo1.bean.User;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DshouquanMapper {
    /**
     * 添加授权信息
     * @param dshouquan
     * @return
     */
   public int addshouquan(Dshouquan dshouquan);

    /**
     * 根据用户id查找
     * @param
     * @return
     */
   public List<Dshouquan> findbyuid(User user);

}
