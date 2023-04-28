package com.svalero.bestread.model;

import android.content.Context;

import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.domain.Book;

public class BookDetailsModel implements BookDetailsContract.Model {

    private Context context;

    public BookDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public Book getBook(String title) {
//        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
//                .allowMainThreadQueries().build();
//        return db.bookDao().getByTitle(title);
        return null;
    }
}
