package com.svalero.bestread.model;

import android.content.Context;
import android.database.sqlite.SQLiteConstraintException;

import com.svalero.bestread.contract.RegisterUserContract;
import com.svalero.bestread.domain.User;

public class RegisterUserModel implements RegisterUserContract.Model {

    private Context context;

    public RegisterUserModel(Context context) {
        this.context =  context;
    }

    @Override
    public boolean registerUser(User user) {
        try {
//            final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
//                    .allowMainThreadQueries().build();
//            db.userDao().insert(user);
            return true;
        }catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
            return false;
        }
    }
}