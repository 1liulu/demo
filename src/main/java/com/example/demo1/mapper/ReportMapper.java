package com.example.demo1.mapper;


import com.example.demo1.bean.Report;
import com.example.demo1.bean.Userimage;

import java.util.List;

public interface ReportMapper {
    /**
     * 获取全部检查报告
     * @return
     */
    public List<Report> findall(Report report);

    /**
     * 添加
     * @param report
     * @return
     */
    public int addreport(Report report);

    /**
     * 修改
     * @param report
     * @return
     */
    public int updatereport(Report report);

    /**
     * 计算当天检查人数
     * @return
     */
    public int countnumber();

    /**
     * 计算当天男女检查人数
     * @param sex
     * @return
     */
    public int tadaycountsex(int sex);

    /**
     * 计算昨天男女检查人数
     * @param sex
     * @return
     */
    public int yesterdaycountsex(int sex);

    /**
     * 查询今天内外科检查人数
     * @param departments
     * @return
     */
    public int tadaycountdepartments(int departments);

    /**
     * 查询今日检查人数
     * @return
     */
    public List<Report> findalls();

    /**
     * 根据病人id查找检查报告
     * @return
     */
    public List<Report> fingbyuid(List<Userimage> list);
}
