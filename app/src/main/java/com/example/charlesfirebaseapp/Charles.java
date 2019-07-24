package com.example.charlesfirebaseapp;

public class Charles {

    private String UserName;
    private String UserImage;

    public Charles() {
    }

    public Charles(String userName, String userImage) {
        UserName = userName;
        UserImage = userImage;
    }

    public String getUserName() {
        return UserName;
    }

    public String getUserImage() {
        return UserImage;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public void setUserImage(String userImage) {
        UserImage = userImage;
    }
}
