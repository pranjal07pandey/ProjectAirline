package com.example.projectairline.Datamodel;

import com.google.gson.annotations.SerializedName;

public class User {

    private int id;
    private String userid;
    private String password;
    private String role;
//    @SerializedName("error")
//    private int error;
private String profilepic;
    private int verified;


    public User(int id, String userid, String password, String role, String profilepic, int verified) {
        this.id = id;
        this.userid = userid;
        this.password = password;
        this.role = role;
        this.profilepic = profilepic;
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

    public String getProfilepic() {
        return profilepic;
    }

    public int getVerified() {
        return verified;
    }
}
