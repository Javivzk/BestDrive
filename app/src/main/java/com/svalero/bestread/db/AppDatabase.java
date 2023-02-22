package com.svalero.bestread.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.bestread.db.dao.BookDao;
import com.svalero.bestread.db.dao.LibraryDao;
import com.svalero.bestread.db.dao.UserDao;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.domain.User;

@Database(entities = {Library.class, Book.class, User.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract LibraryDao libraryDao();

    public abstract UserDao userDao();

    public abstract BookDao bookDao();

}
