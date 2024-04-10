package com.models;

public class Group {
    private int id;
    private String name;
    private String password;
    private boolean isPrivate;

    public Group(int id, String name, String password, boolean isPrivate) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.isPrivate = isPrivate;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isPrivate() {
        return isPrivate;
    }

    public void setPrivate(boolean isPrivate) {
        this.isPrivate = isPrivate;
    }

    
    
}
