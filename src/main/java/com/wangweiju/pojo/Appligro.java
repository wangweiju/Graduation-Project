package com.wangweiju.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Appligro {
    private int id;
    private String topic;
    private String direction;
    private String name;
    private Date stime;//申请时间
    private String status;
    private Date etime;//预计结题时间
    private String fund;//经费

    public Appligro(int id, String topic, String direction, String name, Date stime, String status, Date etime, String fund) {
        this.id = id;
        this.topic = topic;
        this.direction = direction;
        this.name = name;
        this.stime = stime;
        this.status = status;
        this.etime = etime;
        this.fund = fund;
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

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStime() {
        return stime;
    }

    public void setStime(Date stime) {
        this.stime = stime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getEtime() {
        return etime;
    }

    public void setEtime(Date etime) {
        this.etime = etime;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }
}
