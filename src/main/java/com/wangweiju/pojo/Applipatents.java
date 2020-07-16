package com.wangweiju.pojo;


import lombok.Data;

import java.util.Date;

@Data
public class Applipatents {
    private Date time;//申请日期
    private String patentname;//专利名称
    private String applicant;//申请（专利权）人
    private String inventor;//发明（设计）人
    private String summary;//摘要
    private String category;//专利类别
    private String ZLnum;//专利号
    private String CNnum;//公开号
    private String status;//申请状态

    public Applipatents(Date time, String patentname, String applicant, String inventor, String summary, String category, String ZLnum, String CNnum, String status) {
        this.time = time;
        this.patentname = patentname;
        this.applicant = applicant;
        this.inventor = inventor;
        this.summary = summary;
        this.category = category;
        this.ZLnum = ZLnum;
        this.CNnum = CNnum;
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPatentname() {
        return patentname;
    }

    public void setPatentname(String patentname) {
        this.patentname = patentname;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }

    public String getInventor() {
        return inventor;
    }

    public void setInventor(String inventor) {
        this.inventor = inventor;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getZLnum() {
        return ZLnum;
    }

    public void setZLnum(String ZLnum) {
        this.ZLnum = ZLnum;
    }

    public String getCNnum() {
        return CNnum;
    }

    public void setCNnum(String CNnum) {
        this.CNnum = CNnum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
