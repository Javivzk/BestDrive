package com.svalero.bestread.domain;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorites_libraries")
public class FavoritesLibraries {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "library_id")
    private long libraryId;

    @ColumnInfo(name = "library_name")
    private String libraryName;

    @ColumnInfo(name = "library_description")
    private String libraryDescription;

    @ColumnInfo(name = "library_city")
    private String libraryCity;

    public FavoritesLibraries() {
        this.libraryId = libraryId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(long libraryId) {
        this.libraryId = libraryId;
    }

    public String getLibraryName() {
        return libraryName;
    }

    public void setLibraryName(String libraryName) {
        this.libraryName = libraryName;
    }

    public String getLibraryDescription() {
        return libraryDescription;
    }

    public void setLibraryDescription(String libraryDescription) {
        this.libraryDescription = libraryDescription;
    }

    public String getLibraryCity() {
        return libraryCity;
    }

    public void setLibraryCity(String libraryCity) {
        this.libraryCity = libraryCity;
    }
}
