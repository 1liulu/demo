package com.example.demo1.bean;
import java.util.Date;

/**
 * 病人检查报告
 */
public class Report {
    //本系统主键ID
    private int id;
    //系统中病人检查报告唯一id
    private String report_id;
    //关联医院系统中用户唯一id
    private String uid;
    //检查项目
    private String items;
    //申请科室
    private String apply_departments;
    //申请日期
    private Date apply_date;
    //申请医生
    private String apply_doctor;
    //检查所见
    private String examination_finding;
    //印象
    private String impression;
    //检查时间
    private Date inspection_time;
    //报告时间
    private Date report_time;
    //报告医生
    private String report_docto;
    //审核医生
    private String audit_docto;
    //类型
    private String type;
    //病人id
    private String patient_id;
    //检查号
    private String check_no;
    //医院机构
    private String dept;
    //报告名称
    private String name;
    //状态
    private int status;
    //创建时间
    private Date creation_time;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReport_id() {
        return report_id;
    }

    public void setReport_id(String report_id) {
        this.report_id = report_id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getApply_departments() {
        return apply_departments;
    }

    public void setApply_departments(String apply_departments) {
        this.apply_departments = apply_departments;
    }

    public Date getApply_date() {
        return apply_date;
    }

    public void setApply_date(Date apply_date) {
        this.apply_date = apply_date;
    }

    public String getApply_doctor() {
        return apply_doctor;
    }

    public void setApply_doctor(String apply_doctor) {
        this.apply_doctor = apply_doctor;
    }

    public String getExamination_finding() {
        return examination_finding;
    }

    public void setExamination_finding(String examination_finding) {
        this.examination_finding = examination_finding;
    }

    public String getImpression() {
        return impression;
    }

    public void setImpression(String impression) {
        this.impression = impression;
    }

    public Date getInspection_time() {
        return inspection_time;
    }

    public void setInspection_time(Date inspection_time) {
        this.inspection_time = inspection_time;
    }

    public Date getReport_time() {
        return report_time;
    }

    public void setReport_time(Date report_time) {
        this.report_time = report_time;
    }

    public String getReport_docto() {
        return report_docto;
    }

    public void setReport_docto(String report_docto) {
        this.report_docto = report_docto;
    }

    public String getAudit_docto() {
        return audit_docto;
    }

    public void setAudit_docto(String audit_docto) {
        this.audit_docto = audit_docto;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPatient_id() {
        return patient_id;
    }

    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getCheck_no() {
        return check_no;
    }

    public void setCheck_no(String check_no) {
        this.check_no = check_no;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreation_time() {
        return creation_time;
    }

    public void setCreation_time(Date creation_time) {
        this.creation_time = creation_time;
    }
}
