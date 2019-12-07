package com.example.loginpage;

import android.content.Context;
import android.content.SharedPreferences;
import android.service.autofill.UserData;

public class UserDataManager {
    private static final String PREF_NAME = "USER_DATA";
    private static final String KEY_IS_LOGIN = "isLogin";
    private static final String KEY_USERNAME = "username";

    public static Userdata getUserData(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME,Context.MODE_PRIVATE);
        Userdata userData = new Userdata();
        userData.setLogin(preferences.getBoolean(KEY_IS_LOGIN,false));
        userData.setUsername(preferences.getString(KEY_USERNAME,"null"));
        return userData;
    }

    public static void saveUserData(Context context, Userdata userData){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(KEY_IS_LOGIN, userData.isLogin());
        editor.putString(KEY_USERNAME, userData.getUsername());
        editor.apply();
    }

    public static void clearUserData(Context context){
        SharedPreferences preferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear().apply();
    }
}
