package com.wangweiju.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Applila {
    private int id;
    private String num;
    private String applicant;//申请人
    private String topic;//课题名称
    private String status;//申请状态
    private Date stime;//申请时间
    private Date etime;//结束时间

    public Applila(int id, String num, String applicant, String topic, String status, Date stime, Date etime) {
        this.id = id;
        this.num = num;
        this.applicant = applicant;
        this.topic = topic;
        this.status = status;
        this.stime = stime;
        this.etime = etime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }
}
