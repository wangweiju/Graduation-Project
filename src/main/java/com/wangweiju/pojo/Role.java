package com.wangweiju.pojo;

import lombok.Data;

@Data
public class Role {
    private int id;
    private String rolename;
    private int level;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRolename() {
        return rolename;
    }

    public void setRolename(String rolename) {
        this.rolename = rolename;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public Role(int id, String rolename, int level) {
        this.id = id;
        this.rolename = rolename;
        this.level = level;
    }
}
