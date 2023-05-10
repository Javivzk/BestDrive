package com.svalero.bestread.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.svalero.bestread.db.dao.FavoritesDAO;
import com.svalero.bestread.db.dao.UserDao;
import com.svalero.bestread.domain.FavoritesLibraries;
import com.svalero.bestread.domain.User;

@Database(entities = {FavoritesLibraries.class, User.class}, version = 1)
public abstract class BestReadDatabase extends RoomDatabase {
    public abstract FavoritesDAO getFavoriteDAO();

    public abstract UserDao userDao();

}
