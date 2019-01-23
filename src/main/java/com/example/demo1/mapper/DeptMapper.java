package com.example.demo1.mapper;

import com.example.demo1.bean.Dept;

import java.util.List;

public interface DeptMapper {
    /**
     * 根据类型查找
     * @param type
     * @return
     */
    public List<Dept> findbytype(String type);
}
