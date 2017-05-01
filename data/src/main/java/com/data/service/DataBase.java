package com.data.service;

import android.content.Context;
import android.os.Build;

import com.data.BuildConfig;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Juan Delgado on 2/21/2016.
 */
public class DataBase {

    private static Context context;
    private static DataMigration migration;
    private static RealmConfiguration config;

    public static void init(Context ctx) {
        context = ctx;
        migration = new DataMigration();
        config = new RealmConfiguration.Builder(context)
                .name(BuildConfig.DB_NAME)
                .migration(migration)
                .schemaVersion(BuildConfig.DB_NUMBER)
                .build();

    }

    public static Realm getInstance() {
        return Realm.getInstance(config);
    }
}
