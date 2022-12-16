package com.svalero.bestdrive.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.svalero.bestdrive.domain.Notice;

@Database(entities = {Notice.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract NoticeDao noticeDao();
}
