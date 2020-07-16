package com.wangweiju.pojo;

import lombok.Data;

@Data
public class Users {
    private String name;
    private String pwd;
    private int gender;//性别
    private String num;//编号
    private String tell;
    private String email;
    private String perms;//角色权限
    private String subject;//专业

    public Users(String name, String pwd, int gender, String num, String tell, String email, String perms, String subject) {
        this.name = name;
        this.pwd = pwd;
        this.gender = gender;
        this.num = num;
        this.tell = tell;
        this.email = email;
        this.perms = perms;
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getTell() {
        return tell;
    }

    public void setTell(String tell) {
        this.tell = tell;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPerms() {
        return perms;
    }

    public void setPerms(String perms) {
        this.perms = perms;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
