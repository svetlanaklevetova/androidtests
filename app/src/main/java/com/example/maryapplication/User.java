package com.example.maryapplication;

import android.net.Uri;

import java.io.Serializable;

public class User implements Serializable {
    private String mLogin;
    private String mPassword;
    private Uri mPhotoUri;
    private boolean mHasSuccessLogin;

    public User(String login, String password) {
        mLogin = login;
        mPassword = password;
    }

    public String getLogin() {
        return mLogin;
    }

    public void setLogin(String login) {
        mLogin = login;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public Uri getPhotoUri() {
        return mPhotoUri;
    }

    public void setPhotoUri(Uri photoUri) {
        mPhotoUri = photoUri;
    }

    public boolean hasSuccessLogin() {
        return mHasSuccessLogin;
    }

    public void setHasSuccessLogin(boolean hasSuccessLogin) {
        mHasSuccessLogin = hasSuccessLogin;
    }
}
