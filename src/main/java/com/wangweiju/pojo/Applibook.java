package com.wangweiju.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Applibook {

    private int id;//id编号
    private String name;//著作名称
    private String author;//作者
    private String authornum;//第几作者
    private String direction;//研究方向
    private String press;//出版社
    private Date time;//出版时间
    private String num;//书号
    private String status;//申请状态

    public Applibook(int id, String name, String author, String authornum, String direction, String press, Date time, String num, String status) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.authornum = authornum;
        this.direction = direction;
        this.press = press;
        this.time = time;
        this.num = num;
        this.status = status;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthornum() {
        return authornum;
    }

    public void setAuthornum(String authornum) {
        this.authornum = authornum;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
