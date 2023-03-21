package com.svalero.bestread.model;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.svalero.bestread.contract.BestReadContract;
import com.svalero.bestread.contract.BookListContract;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Book;

import java.util.List;

public class BookListModel implements BookListContract.Model {

    private Context context;

    public BookListModel(Context context) {
        this.context = context;
    }
    @Override
    public List<Book> loadAllBooks() {
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        return db.bookDao().getAll();
    }

    @Override
    public Book getByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getById(long id) {
        return null;
    }

    @Override
    public void deleteByTitle(String title) {

    }

    @Override
    public void insert(Book book) {

    }

    @Override
    public void delete(Book book) {

    }

    @Override
    public void update(Book book) {

    }
}