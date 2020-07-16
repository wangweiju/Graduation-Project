package com.wangweiju.pojo;

import lombok.Data;

@Data
public class Meeting {
    private int id;
    private String name;//会议名称
    private String subject;//学科
    private String time;//会议时间
    private String place;//会议地点

    public Meeting(int id, String name, String subject, String time, String place) {
        this.id = id;
        this.name = name;
        this.subject = subject;
        this.time = time;
        this.place = place;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
