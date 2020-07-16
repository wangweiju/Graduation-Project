package com.wangweiju.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class Applipapers {
    private Date time;//发表时间
    private String chname;//中文名
    private String enname;//英文名
    private String author;//论文作者
    private String subject;//学科
    private String publication;//发表刊物
    private String publevel;//刊物级别
    private String pubattributes;//刊物属性
    private String CNnum;//CN刊号
    private String ISSNnum;//ISSN刊号
    private int count;//字数
    private String pagenum;//卷期页
    private String remark;//备注

    public Applipapers(Date time, String chname, String enname, String author, String subject, String publication, String publevel, String pubattributes, String CNnum, String ISSNnum, int count, String pagenum, String remark) {
        this.time = time;
        this.chname = chname;
        this.enname = enname;
        this.author = author;
        this.subject = subject;
        this.publication = publication;
        this.publevel = publevel;
        this.pubattributes = pubattributes;
        this.CNnum = CNnum;
        this.ISSNnum = ISSNnum;
        this.count = count;
        this.pagenum = pagenum;
        this.remark = remark;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getChname() {
        return chname;
    }

    public void setChname(String chname) {
        this.chname = chname;
    }

    public String getEnname() {
        return enname;
    }

    public void setEnname(String enname) {
        this.enname = enname;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getPublication() {
        return publication;
    }

    public void setPublication(String publication) {
        this.publication = publication;
    }

    public String getPublevel() {
        return publevel;
    }

    public void setPublevel(String publevel) {
        this.publevel = publevel;
    }

    public String getPubattributes() {
        return pubattributes;
    }

    public void setPubattributes(String pubattributes) {
        this.pubattributes = pubattributes;
    }

    public String getCNnum() {
        return CNnum;
    }

    public void setCNnum(String CNnum) {
        this.CNnum = CNnum;
    }

    public String getISSNnum() {
        return ISSNnum;
    }

    public void setISSNnum(String ISSNnum) {
        this.ISSNnum = ISSNnum;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPagenum() {
        return pagenum;
    }

    public void setPagenum(String pagenum) {
        this.pagenum = pagenum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
