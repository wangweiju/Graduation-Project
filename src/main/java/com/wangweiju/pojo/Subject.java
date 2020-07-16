package com.wangweiju.pojo;

import lombok.Data;

@Data
public class Subject {
    private int id;
    private String subname;//学科名

    public Subject(int id, String subname) {
        this.id = id;
        this.subname = subname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSubname() {
        return subname;
    }

    public void setSubname(String subname) {
        this.subname = subname;
    }
}
