package com.svalero.bestread.model;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.svalero.bestread.contract.LibraryDetailsContract;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Library;

public class LibraryDetailsModel implements LibraryDetailsContract.Model {

    private Context context;

    public LibraryDetailsModel(Context context) {
        this.context = context;
    }

    @Override
    public Library getLibrary(String name) {
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        return db.libraryDao().getByName(name);
    }
}
