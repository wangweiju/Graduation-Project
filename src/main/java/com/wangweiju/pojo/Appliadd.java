package com.wangweiju.pojo;

import lombok.Data;

@Data
public class Appliadd {
    private int id;
    private String topic;
    private String leader;
    private String member;
    private String status;
    private String name;

    public Appliadd(int id, String topic, String leader, String member, String status, String name) {
        this.id = id;
        this.topic = topic;
        this.leader = leader;
        this.member = member;
        this.status = status;
        this.name = name;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
