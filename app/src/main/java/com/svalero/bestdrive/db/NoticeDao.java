package com.svalero.bestdrive.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.bestdrive.domain.Notice;

import java.util.List;

@Dao
public interface NoticeDao {

    @Query("SELECT * FROM notice")
    List<Notice> getAll();

    @Query("SELECT * FROM notice WHERE name = :name")
    Notice getByName(String name);

    @Query("DELETE FROM notice WHERE name = :name")
    void deleteByName(String name);

    @Insert
    void insert(Notice notice);

    @Delete
    void delete(Notice notice);

    @Update
    void update(Notice notice);
}
