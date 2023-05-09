package com.svalero.bestread.domain;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
public class Book {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo
    private String code;

    @NonNull
    private String title;
    @ColumnInfo
    private String author;

    @ColumnInfo
    private String year;

    @ColumnInfo
    private String genre;

    @ColumnInfo
    private String description;


    @ColumnInfo
    private int pages;
    @ColumnInfo
    private float price;

    @ColumnInfo
    private boolean hasStock;

    public Book() {
    }

    public Book(String code, String title, String author, String year, String genre, String description, int pages, float price, boolean hasStock) {
        this.code = code;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.pages = pages;
        this.price = price;
        this.hasStock = hasStock;
        this.description = description;
    }

    public Book(String title, String author, String description, String code, String year, String genre) {
        this.code = code;
        this.title = title;
        this.author = author;
        this.year = year;
        this.genre = genre;
        this.description = description;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public boolean isHasStock() {
        return hasStock;
    }

    public void setHasStock(boolean hasStock) {
        this.hasStock = hasStock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle( String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }



}