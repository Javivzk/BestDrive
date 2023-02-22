package com.svalero.bestread.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.svalero.bestread.domain.Book;

import java.util.List;

@Dao
public interface BookDao {

    @Query("SELECT * FROM book")
    List<Book> getAll();

    @Query("SELECT * FROM book WHERE title = :title")
    Book getByTitle(String title);

    @Query("SELECT * FROM book WHERE id = :id")
    Book getById(long id);

    @Query("DELETE FROM book WHERE title = :title")
    void deleteByTitle(String title);

    @Insert
    void insert(Book book);

    @Delete
    void delete(Book book);

    @Update
    void update(Book book);

}
