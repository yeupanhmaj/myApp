package com.example.vocalearn.SharedReference;

import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;

import com.example.vocalearn.MyApplication;

public class MyRF {
    public static void SaveInt(SharedPreferences sp,String key, int value) {

        sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public static int LoadInt(SharedPreferences sp,String key) {
        sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        return sp.getInt(key, 0);
    }

    public static void SaveString(SharedPreferences sp,String key, String value) {

        sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public static String LoadString(SharedPreferences sp,String key) {
        sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());
        return sp.getString(key, "Enter your name here");
    }
    public static void clear(SharedPreferences sp)
    {
        sp = PreferenceManager.getDefaultSharedPreferences(MyApplication.getContext());// here you get your prefrences by either of two methods
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.commit();
    }
}
