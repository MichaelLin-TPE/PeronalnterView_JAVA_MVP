package com.money.peronainterviewproject_java_mvp.account;

import android.content.Context;
import android.content.SharedPreferences;

import com.money.peronainterviewproject_java_mvp.MyApplication;

public class SharedPreferencesUtils {

    public static SharedPreferences getSharedPreferences(String key){
        return MyApplication.instance.getSharedPreferences(key, Context.MODE_PRIVATE);
    }
    public static void removeData(String parent, String key){
        if (getSharedPreferences(parent).contains(key)){
            getSharedPreferences(parent).edit().remove(key).apply();
        }
    }

    public static void removeAll(String parent){
        getSharedPreferences(parent).edit().clear().apply();
    }
}
