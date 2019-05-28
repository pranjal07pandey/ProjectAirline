package com.example.projectairline;

public class Upload {

    private String mName;
    private String mImageuri;

    public Upload(String mName, String mImageuri) {
        if (mName.trim().equals("")){
            mName = "No Name";
        }
        this.mName = mName;
        this.mImageuri = mImageuri;
    }

    public Upload(){

        //empty constructor
    }

    public String getmName() {
        return mName;
    }

    public String getmImageuri() {
        return mImageuri;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public void setmImageuri(String mImageuri) {
        this.mImageuri = mImageuri;
    }
}
