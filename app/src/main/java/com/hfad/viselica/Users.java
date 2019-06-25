package com.hfad.viselica;

import android.support.v7.app.AppCompatActivity;

public class Users extends AppCompatActivity {
    private String userName;
    private String userIndex;

    Users(String userName, String userIndex) {
        this.userName = userName;
        this.userIndex = userIndex;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserIndex() {
        return userIndex;
    }

}
