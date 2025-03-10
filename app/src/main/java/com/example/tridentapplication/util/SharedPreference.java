package com.example.tridentapplication.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;

import java.util.HashMap;
public class SharedPreference {
    private static final String USER_PREFS = "SeekingDaddie";
    private SharedPreferences appSharedPrefs;
    private SharedPreferences.Editor prefsEditor;

    public SharedPreference(Context context) {
        this.appSharedPrefs = context.getSharedPreferences(USER_PREFS, Activity.MODE_PRIVATE);
        this.prefsEditor = appSharedPrefs.edit();
    }

    public int getValue_int(String intKeyValue) {
        return appSharedPrefs.getInt(intKeyValue, 0);
    }

    public String getValue_string(String stringKeyValue) {
        return appSharedPrefs.getString(stringKeyValue, "");
    }

    public Boolean getValue_boolean(String stringKeyValue) {
        return appSharedPrefs.getBoolean(stringKeyValue, false);
    }

    public void setValue_int(String intKeyValue, int _intValue) {

        prefsEditor.putInt(intKeyValue, _intValue).commit();
    }

    public void setValue_string(String stringKeyValue, String _stringValue) {

        prefsEditor.putString(stringKeyValue, _stringValue).commit();
    }


    public void getValue_string(String stringKeyValue, String _stringValue) {

        prefsEditor.putString(stringKeyValue, _stringValue).commit();
    }

    public void setValue_boolean(String stringKeyValue, Boolean _bool) {

        prefsEditor.putBoolean(stringKeyValue, _bool).commit();
    }

    public void setValue_int(String intKeyValue, HashMap<String, String> stringStringHashMap) {
    }

    public void clearData() {
        prefsEditor.clear().commit();
    }
}