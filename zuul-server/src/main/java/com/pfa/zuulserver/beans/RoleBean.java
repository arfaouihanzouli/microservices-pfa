package com.pfa.zuulserver.beans;

public class RoleBean {
    private long id;
    private String role;

    public RoleBean() {
    }

    public RoleBean(String role) {
        this.role = role;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
