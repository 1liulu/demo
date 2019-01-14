package com.example.demo1.service;

import com.example.demo1.bean.Report;
import com.example.demo1.bean.Userimage;
import com.example.demo1.mapper.ReportMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportService {
    @Autowired
    ReportMapper reportMapper;

    /**
     * 获取全部检查报告
     * @param report
     * @return
     */
    public List<Report> findall(Report report){
        List<Report> reportList=reportMapper.findall(report);
        return reportList;
    }

    /**
     * 添加
     * @param report
     * @return
     */
    public int addreport(Report report){
        return reportMapper.addreport(report);
    }

    /**
     * 修改
     * @param report
     * @return
     */
    public int updatereport(Report report){
        return  reportMapper.updatereport(report);
    }

    /**
     * 计算当天检查人数
     * @return
     */
    public int countnumber(){
        return reportMapper.countnumber();
    }

    /**
     * 计算当天男女检查人数
     * @param sex
     * @return
     */
    public int tadaycountsex(int sex){
        return reportMapper.tadaycountsex(sex);
    }

    /**
     * 计算昨天男女检查人数
     * @param sex
     * @return
     */
    public int yesterdaycountsex(int sex){
        return reportMapper.yesterdaycountsex(sex);
    }

    /**
     * 查询今天内外科检查人数
     * @param departments
     * @return
     */
    public int tadaycountdepartments(int departments){
        return  reportMapper.tadaycountdepartments(departments);
    }

    /**
     * 查询今日检查
     * @return
     */
    public List<Report> findalls(){
        return reportMapper.findalls();
    }

    /**
     * 根据病人id查找检查报告
     * @param uid
     * @return
     */
    public List<Report> fingbyuid(List<Userimage> list){
        return reportMapper.fingbyuid(list);
    }
}
