package com.wangweiju.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Meetingsitu {
    private int id;//序号
    private String name;//会议名称
    private Date time;//会议时间
    private String place;//会议地点
    private int ynum;//应到人数
    private int snum;//实到人数
    private int count;//缺席人数

    public Meetingsitu(int id, String name, Date time, String place, int ynum, int snum, int count) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.place = place;
        this.ynum = ynum;
        this.snum = snum;
        this.count = count;
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getYnum() {
        return ynum;
    }

    public void setYnum(int ynum) {
        this.ynum = ynum;
    }

    public int getSnum() {
        return snum;
    }

    public void setSnum(int snum) {
        this.snum = snum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
