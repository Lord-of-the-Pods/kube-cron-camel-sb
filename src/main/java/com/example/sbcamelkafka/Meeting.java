package com.example.sbcamelkafka;

public class Meeting {

    String vm_request_id;
    String visitor_id;
    String host_employee;
    String purpose_of_visit;
    String employee_email;
    String start_time;
    String end_time;
    String created_at;
    String office_loc;
    String accompany;
    String status;

    public String getVm_request_id() {
        return vm_request_id;
    }

    public void setVm_request_id(String vm_request_id) {
        this.vm_request_id = vm_request_id;
    }

    public String getVisitor_id() {
        return visitor_id;
    }

    public void setVisitor_id(String visitor_id) {
        this.visitor_id = visitor_id;
    }

    public String getHost_employee() {
        return host_employee;
    }

    public void setHost_employee(String host_employee) {
        this.host_employee = host_employee;
    }

    public String getPurpose_of_visit() {
        return purpose_of_visit;
    }

    public void setPurpose_of_visit(String purpose_of_visit) {
        this.purpose_of_visit = purpose_of_visit;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getOffice_loc() {
        return office_loc;
    }

    public void setOffice_loc(String office_loc) {
        this.office_loc = office_loc;
    }

    public String getAccompany() {
        return accompany;
    }

    public void setAccompany(String accompany) {
        this.accompany = accompany;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
