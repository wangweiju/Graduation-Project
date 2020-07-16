package com.wangweiju.pojo;

import lombok.Data;

@Data
public class Journal {
    private int id;//序号
    private String name;//期刊名
    private String subject;//学科
    private String ISSN;//ISSN号
    private String CN;//CN号
    private String details;//详情

    public Journal(int id, String name, String subject, String ISSN, String CN, String details) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.ISSN = ISSN;
        this.CN = CN;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getISSN() {
        return ISSN;
    }

    public void setISSN(String ISSN) {
        this.ISSN = ISSN;
    }

    public String getCN() {
        return CN;
    }

    public void setCN(String CN) {
        this.CN = CN;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
