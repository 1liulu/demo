package com.example.demo1.mapper;


import com.example.demo1.bean.Report;

import java.util.List;

public interface ReportMapper {
    /**
     * 获取全部检查报告
     * @return
     */
    public List<Report> findall(Report report);

}
