package com.svalero.bestdrive.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.bestdrive.db.dao.LibraryDao;
import com.svalero.bestdrive.db.dao.UserDao;
import com.svalero.bestdrive.domain.Library;
import com.svalero.bestdrive.domain.User;

@Database(entities = {Library.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LibraryDao libraryDao();

    public abstract UserDao userDao();
}
