package com.example.projectairline.Datamodel;

public class User {

    private int id;
    private String userid;
    private String password;
    private String role;
    private boolean error;
    private int verified;


    public User(int id, String userid, String password, String role, boolean error, int verified) {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.role = role;
        this.error = error;
        this.verified = verified;
    }

    public int getId() {
        return id;
    }

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String getRole() {
        return role;
    }

    public boolean getError() {
        return error;
    }

    public int getVerified() {
        return verified;
    }
}
