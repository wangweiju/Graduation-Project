package com.wangweiju.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Laboratory {
    private int id;
    private String num;
    private int status;
    private int pro_id;
    private String topic;
    private Date applicationtime;//开题时间
    private Date endtime;//结题时间
    private String leader;//课题组组长

    public Laboratory(int id, String num, int status, int pro_id, String topic, Date applicationtime, Date endtime, String leader) {
        this.id = id;
        this.num = num;
        this.status = status;
        this.pro_id = pro_id;
        this.topic = topic;
        this.applicationtime = applicationtime;
        this.endtime = endtime;
        this.leader = leader;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPro_id() {
        return pro_id;
    }

    public void setPro_id(int pro_id) {
        this.pro_id = pro_id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Date getApplicationtime() {
        return applicationtime;
    }

    public void setApplicationtime(Date applicationtime) {
        this.applicationtime = applicationtime;
    }

    public Date getEndtime() {
        return endtime;
    }

    public void setEndtime(Date endtime) {
        this.endtime = endtime;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }
}
