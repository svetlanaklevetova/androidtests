package com.example.maryapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class SharedPreferencesHelper {

    public static final String SHARED_PREF_NAME = "SHARED_PREF_NAME";
    public static final String USERS_KEY = "USERS_KEY";
    public static final Type USERS_TYPE = new TypeToken<List<User>>() {
    }.getType();

    private Gson gson = new Gson();

    private SharedPreferences mHelper;

    public SharedPreferencesHelper(Context context) {
        mHelper = context.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
    }

    public List<User> getUsers()
    {
        List<User> users = gson.fromJson(mHelper.getString(USERS_KEY, ""), USERS_TYPE);
        return users == null ? new ArrayList<User>() : users;
    }

    public boolean addUser (User user)
    {
        List<User> users = getUsers();
        for(User u : users)
        {
            if(u.getLogin().equalsIgnoreCase(user.getLogin()  ))
                return false;
        }


        users.add(user);
        mHelper.edit().putString(USERS_KEY, gson.toJson(users, USERS_TYPE)).apply();
        return true;
    }
}
