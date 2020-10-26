package com.example.profitplus.constant;

import android.content.Context;
import android.content.SharedPreferences;
import com.example.profitplus.model.UserModel;
public class PreferenceManager {

    private static PreferenceManager mInstance;
    private static Context ctx;
    private static final String PREFS_NAME = "profitplusdatabase";
    private static final String LOGIN_STATUS = "false";
    private static final String TOKEN = "token";
    private static final String FIRST_NAME = "firstname";
    private static final String LAST_NAME = "lastname";
    private static final String EMAIL_ID = "profitplus@gmail.com";
    private static final String MOBILE_NUMBER = "0000000";
    private static final String GENDER = "None";
    private static final String COUNTRY = "India";
    private static final String ID = "0";

    public PreferenceManager(Context context) {
        ctx = context;
    }

    public static synchronized PreferenceManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new PreferenceManager(context);
        }
        return mInstance;
    }

    public void userLogin(UserModel user) {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(ID, user.getId());
        editor.putString(FIRST_NAME, user.getfName());
        editor.putString(LAST_NAME, user.getlName());
        editor.putString(EMAIL_ID, user.getEmail());
        editor.putString(MOBILE_NUMBER, user.getMobile());
        editor.putString(GENDER, user.getGender());
        editor.putBoolean(LOGIN_STATUS, user.getLoginStatus());
        editor.putString(TOKEN, user.getToken());
        editor.apply();
    }

    public UserModel getCustomer() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return new UserModel(
                sharedPreferences.getString(ID, "NA"),
                sharedPreferences.getString(FIRST_NAME, "NA"),
                sharedPreferences.getString(LAST_NAME, "NA"),
                sharedPreferences.getString(EMAIL_ID, "NA"),
                sharedPreferences.getString(MOBILE_NUMBER,"NA"),
                sharedPreferences.getString(GENDER, "NA"),
                sharedPreferences.getString(TOKEN, "NA"),
                sharedPreferences.getBoolean(LOGIN_STATUS, false)
        );
    }

    public static void clear() {
        SharedPreferences sharedPreferences = ctx.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
}
