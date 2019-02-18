package com.example.demo1.bean;

public class OrderDetails {
    private User u_user;
    private User d_user;
    private Order order;
    private Report report;
    private ConsultationReport consultationReport;

    public User getU_user() {
        return u_user;
    }

    public void setU_user(User u_user) {
        this.u_user = u_user;
    }

    public User getD_user() {
        return d_user;
    }

    public void setD_user(User d_user) {
        this.d_user = d_user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }

    public ConsultationReport getConsultationReport() {
        return consultationReport;
    }

    public void setConsultationReport(ConsultationReport consultationReport) {
        this.consultationReport = consultationReport;
    }
}
