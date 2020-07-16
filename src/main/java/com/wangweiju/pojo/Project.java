package com.wangweiju.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Project {
    private int id;
    private String proname;//项目名
    private String resgroup;//隶属课题组
    private String host;//项目主持人
    private String member;//项目成员
    private Date stime;//开始时间
    private Date etime;//结束时间

    public Project(int id, String proname, String resgroup, String host, String member, Date stime, Date etime) {
        this.id = id;
        this.proname = proname;
        this.resgroup = resgroup;
        this.host = host;
        this.member = member;
        this.stime = stime;
        this.etime = etime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProname() {
        return proname;
    }

    public void setProname(String proname) {
        this.proname = proname;
    }

    public String getResgroup() {
        return resgroup;
    }

    public void setResgroup(String resgroup) {
        this.resgroup = resgroup;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
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
