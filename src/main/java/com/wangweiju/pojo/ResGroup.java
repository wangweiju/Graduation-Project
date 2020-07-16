package com.wangweiju.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class ResGroup {
    private int id;
    private String topic;
    private String researchdirection;
    private String leader;
    private String member;
    private Date applicationtime;//开题时间
    private int status;
    private String fund;
    private Date endtime;//结题时间

    public ResGroup(int id, String topic, String researchdirection, String leader, String member, Date applicationtime, int status, String fund, Date endtime) {
        this.id = id;
        this.topic = topic;
        this.researchdirection = researchdirection;
        this.leader = leader;
        this.member = member;
        this.applicationtime = applicationtime;
        this.status = status;
        this.fund = fund;
        this.endtime = endtime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getResearchdirection() {
        return researchdirection;
    }

    public void setResearchdirection(String researchdirection) {
        this.researchdirection = researchdirection;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public Date getApplicationtime() {
        return applicationtime;
    }

    public void setApplicationtime(Date applicationtime) {
        this.applicationtime = applicationtime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }
}
