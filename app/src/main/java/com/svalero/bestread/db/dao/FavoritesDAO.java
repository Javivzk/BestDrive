package com.svalero.bestread.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.svalero.bestread.domain.FavoritesLibraries;

import java.util.List;

@Dao
public interface FavoritesDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FavoritesLibraries favorites);

    @Query("SELECT * FROM favorites_libraries")
    List<FavoritesLibraries> getAll();

    @Delete
    void delete(FavoritesLibraries favorites);

    @Query("DELETE FROM favorites_libraries")
    void deleteAll();

}
