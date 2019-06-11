package com.example.syyam.jobsapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Config {
    public static Context APPLICATION_CONTEXT = null;

    //API Links
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    public static final String BASE_URL = "http://68.183.81.182/api/v1/u/";


    public static String getToken(Context context){
        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText ="Bearer"+ prefs.getString("token", null);
        return restoredText;
    }
}
