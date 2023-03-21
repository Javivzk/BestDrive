package com.svalero.bestread.model;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.contract.LibraryDetailsContract;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

public class BookDetailsModel implements BookDetailsContract.Model {

    private Context context;

    public BookDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public Book getBook(String title) {
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        return db.bookDao().getByTitle(title);
    }
}
