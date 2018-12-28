package com.example.demo1.Contorller;


import com.example.demo1.bean.Patient;
import com.example.demo1.bean.Report;
import com.example.demo1.service.PatientService;
import com.example.demo1.service.ReportService;
import com.example.demo1.util.Constant;
import com.example.demo1.util.MsgBuilder;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/add")
public class AddController {
    @Autowired
    ReportService reportService;
    @Autowired
    PatientService patientService;
    @RequestMapping(value = "/addreport")
    public Map add(String report_id, String uid, String age, String sex, String username, String phone,
                   String items, String apply_departments, String apply_date, String apply_doctor, String examination_finding, String impression,
                   String inspection_time, String report_time, String report_docto, String audit_docto, String type, String patient_id,
                   String check_no,String name,String dept) {
        if (StringUtils.isBlank(report_id)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(uid)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(age)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(sex)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(username)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(phone)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(items)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(apply_departments)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(apply_date)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(apply_doctor)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(examination_finding)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(impression)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(inspection_time)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(report_time)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(report_docto)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(audit_docto)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(patient_id)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(check_no)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(check_no)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        if (StringUtils.isBlank(dept)) {
            return MsgBuilder.buildReturnErrorMessage("");
        }
        Report report = new Report();
        report.setReport_id(report_id);
        //查询系统中有没有该报告
        List<Report> reportList = reportService.findall(report);
        report.setCreation_time(new Date());
        report.setStatus(Constant.STATUS_VALID);
        report.setItems(items);
        report.setApply_departments(apply_departments);
        report.setApply_date(new Date());
        report.setApply_doctor(apply_doctor);
        report.setExamination_finding(examination_finding);
        report.setImpression(impression);
        report.setInspection_time(new Date());
        report.setReport_time(new Date());
        report.setReport_docto(report_docto);
        report.setAudit_docto(audit_docto);
        report.setType(type);
        report.setPatient_id(patient_id);
        report.setCheck_no(check_no);
        report.setName(name);
        report.setDept(dept);
        report.setUid(uid);
        report.setReport_id(report_id);
        if (reportList == null || reportList.size() == 0) {
            //没有 添加
            //查看该病人是否在数据库中
            Patient patient = new Patient();
            patient.setUid(uid);
            patient.setStatus(Constant.STATUS_VALID);
            List<Patient> patientList = patientService.findbyuid(patient);
            if (patientList == null || patientList.size() == 0) {
                //不在数据库中
                //添加
                patient.setCreation_time(new Date());
                patient.setAge(Integer.parseInt(age));
                patient.setSex(Integer.parseInt(sex));
                patient.setPhone(phone);
                patient.setName(username);
                patientService.addpatient(patient);
            } else {
                //在数据库中
            }
            reportService.addreport(report);
        } else {
            //有更新
            reportService.updatereport(report);
        }


        return MsgBuilder.buildReturnMessage("成功");
    }

}
