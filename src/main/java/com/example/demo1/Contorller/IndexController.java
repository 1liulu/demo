package com.example.demo1.Contorller;


import com.example.demo1.bean.Account;
import com.example.demo1.bean.Report;
import com.example.demo1.service.ReportService;
import com.example.demo1.util.MsgBuilder;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/index")
public class IndexController {
    @Autowired
    ReportService reportService;

    @RequestMapping(value = "/show")
    public Map show(String page, String size, String name, HttpServletRequest request) {
        Integer pageNum=1;
        Integer pageSize=1;
        if (!StringUtils.isBlank(page) ) {
            pageNum=Integer.parseInt(page);
        }
        if (!StringUtils.isBlank(size) ) {
            pageSize=Integer.parseInt(size);
        }
        PageHelper.startPage(pageNum, pageSize);
        Report report=new Report();
        if (!StringUtils.isBlank(name) ) {
            report.setUsername("%"+name+"%");
        }
        Account account=(Account) request.getAttribute("account");
        report.setType(account.getType());
        List<Report> reportList=reportService.findall(report);
        PageInfo<Report> pageInfo = new PageInfo<>(reportList);
        return MsgBuilder.buildReturnMessage(pageInfo);
    }


}
