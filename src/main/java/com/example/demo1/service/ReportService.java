package com.example.demo1.service;

import com.example.demo1.bean.Report;
import com.example.demo1.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportMapper reportMapper;
    public List<Report> findall(Report report){
        List<Report> reportList=reportMapper.findall(report);
        return reportList;
    }
    public int addreport(Report report){
        return reportMapper.addreport(report);
    }
    public int updatereport(Report report){
        return  reportMapper.updatereport(report);
    }
}
