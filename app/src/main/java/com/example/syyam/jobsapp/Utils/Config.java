package com.example.syyam.jobsapp.Utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Config {
    public static Context APPLICATION_CONTEXT = null;

    //API Links
    public static final String MY_PREFS_NAME = "MyPrefsFile";

    public static final String IMAGE_URL = "http://68.183.81.182/images/";
    public static final String BASE_URL = "http://68.183.81.182/api/v1/u/";
    public static final String BASE_URL_AUTH = "http://68.183.81.182/api/v1/a/";




    // use token in http://68.183.81.182/api/v1/a/
    public static String getToken(Context context) {


        SharedPreferences prefs = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String restoredText ="Bearer "+ prefs.getString("token", null);

//        SharedPreferences.Editor editor = context.getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
//        editor.putString("bearerToken", restoredText);
//        editor.apply();

        return restoredText; //restoredText = "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOjEsImlhdCI6MTU2MDI4MzkwM30.51TXc7KrYYoOr_OrddcT3LR_GuIiDISQ9Rvy1hFliRA";
    }
}
