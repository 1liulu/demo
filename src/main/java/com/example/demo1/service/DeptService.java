package com.example.demo1.service;

import com.example.demo1.bean.Dept;
import com.example.demo1.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeptService {
    @Autowired
    DeptMapper deptMapper;
    public List<Dept> findbytype(String type){
        return deptMapper.findbytype(type);
    }
}
