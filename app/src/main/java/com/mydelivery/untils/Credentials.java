package com.mydelivery.untils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;



public class Credentials {
    private static final String APP_KEY = "my_delivery";

    private static final String USER_LOGIN = "login";
    private static final String USER_ID = "user_id";




    public static void SaveUserID(String id, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(USER_ID, id);
        editor.commit();
    }

    public static String getUserID(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(APP_KEY, Activity.MODE_PRIVATE);
        return preferences.getString(USER_ID, "");
    }

}
