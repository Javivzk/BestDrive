package com.svalero.bestread.model;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Room;

import com.svalero.bestread.contract.RegisterLibraryContract;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Library;

public class RegisterLibraryModel implements RegisterLibraryContract.Model {

    private Context context;

    public RegisterLibraryModel(Context context) {
        this.context =  context;
    }

    @Override
    public boolean registerLibrary(Library library) {
        try {
            final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
            db.libraryDao().insert(library);
            return true;
        }catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
            return false;
        }
    }
}
