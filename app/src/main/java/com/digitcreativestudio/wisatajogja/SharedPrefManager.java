package com.digitcreativestudio.wisatajogja;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefManager {

    private static final String SHARE_PREF_NAME = "user";
    private static final String KEY_NAME = "iduser";
    private static final String KEY_EMAIL = "idroleuser";
    private static final String KEY_PASSWORD = "nameuser";
    private static SharedPrefManager mInstance;
    private static Context mContext;

    private SharedPrefManager(Context context) {
        mContext = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }

    public boolean setLogin(String emailUser) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_EMAIL, emailUser);

        editor.apply();

        return true;
    }

    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_EMAIL, null) != null;
    }

    public boolean logout() {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        return true;
    }

//    public String getStringName() {
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_NAME, null);
//    }

//    public String getStringEmail() {
//        SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARE_PREF_NAME, Context.MODE_PRIVATE);
//        return sharedPreferences.getString(KEY_EMAIL, null);
//    }

}