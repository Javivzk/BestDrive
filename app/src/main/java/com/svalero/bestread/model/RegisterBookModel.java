package com.svalero.bestread.model;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Room;

import com.svalero.bestread.contract.RegisterBookContract;
import com.svalero.bestread.contract.RegisterLibraryContract;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

public class RegisterBookModel implements RegisterBookContract.Model {

    private Context context;

    public RegisterBookModel(Context context) {
        this.context =  context;
    }

    @Override
    public boolean registerBook(Book book) {
        try {
            final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
            db.bookDao().insert(book);
            return true;
        }catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
            return false;
        }
    }
}