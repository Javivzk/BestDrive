package com.svalero.bestread.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.bestread.domain.Library;

import java.util.List;

@Dao
public interface LibraryDao {

    @Query("SELECT * FROM library")
    List<Library> getAll();

    @Query("SELECT * FROM library WHERE name = :name")
    Library getByName(String name);

    @Query("SELECT * FROM library WHERE id = :id")
    Library getById(long id);

    @Query("DELETE FROM library WHERE name = :name")
    void deleteByName(String name);

    @Insert
    void insert(Library library);

    @Delete
    void delete(Library library);

    @Update
    void update(Library library);

}
