package com.data.service;

import android.content.Context;
import android.content.SharedPreferences;

import com.data.BuildConfig;
import com.google.gson.Gson;

/**
 * Created by juan.delgado on 02/03/2017.
 */

public class PreferencesData {

    private static SharedPreferences sharedPreferences;
    private static PreferencesData instance;

    public static void init(Context ctx) {
        if (sharedPreferences == null) {
            sharedPreferences = ctx.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
        }
        if (instance == null)
            instance = new PreferencesData();
    }

    public static PreferencesData getInstance() {
        if (instance == null)
            instance = new PreferencesData();
        return instance;
    }


    public void saveObject(String key, Object obj) throws Exception {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        prefsEditor.putString(key, json);
        prefsEditor.apply();
    }

    public Object getObject(String key, Class clazz) throws IllegalAccessException, InstantiationException {
        Gson gson = new Gson();
        String json = sharedPreferences.getString(key, gson.toJson(clazz.newInstance(), clazz));
//        String json = sharedPreferences.getString(key, null);
        return gson.fromJson(json, clazz);
    }

    public void saveBoolean(String key, boolean obj) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putBoolean(key, obj);
        prefsEditor.apply();
    }

    public static boolean getBoolean(String key) {
        return sharedPreferences.getBoolean(key, true);
    }

    public long getLong(String key) {
        return sharedPreferences.getLong(key, 0);
    }
    public void setLong(String key,long date) {
        SharedPreferences.Editor prefsEditor = sharedPreferences.edit();
        prefsEditor.putLong(key, date);
        prefsEditor.apply();
    }
}
