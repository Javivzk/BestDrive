package com.svalero.bestdrive.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.bestdrive.domain.Library;
import com.svalero.bestdrive.domain.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user")
    List<User> getAll();

    @Query("SELECT * FROM user WHERE username = :username")
    User getByUsername(String username);

    @Query("SELECT * FROM user WHERE id = :id")
    User getById(String id);

    @Query("SELECT * FROM user WHERE name = :name")
    User getByName(String name);

    @Query("SELECT * FROM user WHERE username = :username AND password = :password")
    User login(String username, String password);

    @Query("UPDATE user SET pathImg = :pathImg WHERE username = :username")
    void updateImgUser(String username, String pathImg);

    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);
}
