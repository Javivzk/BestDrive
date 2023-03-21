package com.svalero.bestread.model;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import androidx.room.Room;

import com.svalero.bestread.contract.RegisterBookContract;
import com.svalero.bestread.contract.RegisterUserContract;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.User;

public class RegisterUserModel implements RegisterUserContract.Model {

    private Context context;

    public RegisterUserModel(Context context) {
        this.context =  context;
    }

    @Override
    public boolean registerUser(User user) {
        try {
            final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                    .allowMainThreadQueries().build();
            db.userDao().insert(user);
            return true;
        }catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
            return false;
        }
    }
}