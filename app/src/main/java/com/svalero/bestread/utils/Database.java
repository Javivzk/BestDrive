package com.svalero.bestread.utils;

import static com.svalero.bestread.api.Constants.DATABASE_NAME;

import android.app.Application;

import androidx.room.Room;

import com.svalero.bestread.db.BestReadDatabase;


public class Database extends Application {

    private BestReadDatabase database;
    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(), BestReadDatabase.class, DATABASE_NAME).build();
    }

    public BestReadDatabase getDatabase(){
        return database;
    }
}
