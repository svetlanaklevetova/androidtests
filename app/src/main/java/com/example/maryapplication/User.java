package com.example.maryapplication;

import java.io.Serializable;

public class User implements Serializable {
    private String mLogin;
    private String mPassword;

    public User(String login, String password) {
        mLogin = login;
        mPassword = password;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setmLogin(String mLogin) {
        this.mLogin = mLogin;
    }
}
