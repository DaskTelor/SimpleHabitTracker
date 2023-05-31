package com.dasktelor.simplehabittracker;

import android.app.Application;

import androidx.room.Room;

import com.dasktelor.simplehabittracker.db.AppDatabase;

public class App extends Application {
    private AppDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, AppDatabase.class, "database")
                .build();
    }
    public AppDatabase getDatabase() {
        return database;
    }

}
