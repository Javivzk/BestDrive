package com.svalero.bestread.view;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.bestread.R;
import com.svalero.bestread.domain.User;

public class ModifyUserActivity extends AppCompatActivity {


    User user;

    EditText et_modifyUserId;

    EditText et_modifyUserName;
    EditText et_modifyUserUsername;
    EditText et_modifyUserPassword;
    EditText et_modifyUserEmail;

    EditText et_modifyUserLastName;

    EditText et_modifyUserPhone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_user);

//        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
//                .allowMainThreadQueries().build();
//        user = db.userDao().getByUsername(LoginActivity.currentUser);

        et_modifyUserId = findViewById(R.id.et_modifyUserId);
        et_modifyUserName = findViewById(R.id.et_modifyUserName);
        et_modifyUserUsername = findViewById(R.id.et_modifyUserUsername);
        et_modifyUserPassword = findViewById(R.id.et_modifyUserPassword);
        et_modifyUserEmail = findViewById(R.id.et_modifyUserEmail);
        et_modifyUserPhone = findViewById(R.id.et_modifyUserPhone);
        et_modifyUserLastName = findViewById(R.id.et_modifyUserLastName);

        et_modifyUserId = (EditText) findViewById((int) user.getId());
        et_modifyUserName.setText(user.getName());
        et_modifyUserUsername.setText(user.getUserName());
        et_modifyUserPassword.setText(user.getPassword());
        et_modifyUserEmail.setText(user.getEmail());
        et_modifyUserPhone.setText(user.getPhone());
        et_modifyUserLastName.setText(user.getLastName());
    }

//    public void modifyUser(View view) {
//
//        String name = et_modifyUserName.getText().toString();
//        String lastname = et_modifyUserLastName.getText().toString();
//        String username = et_modifyUserUsername.getText().toString();
//        String password = et_modifyUserPassword.getText().toString();
//        String phone = et_modifyUserPhone.getText().toString();
//        String email = et_modifyUserEmail.getText().toString();
//
//
//        User newUser = new User(username, password, name, lastname,email, phone);
//
//        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
//                .allowMainThreadQueries().build();
//
//        try {
//            db.userDao().update(newUser);
//            LoginActivity.currentUser = newUser.getUserName();
//
//            Snackbar.make(et_modifyUserName, R.string.update_user, BaseTransientBottomBar.LENGTH_LONG).show();
//
//            et_modifyUserName.setText("");
//            et_modifyUserUsername.setText("");
//            et_modifyUserPassword.setText("");
//            et_modifyUserLastName.setText("");
//            et_modifyUserEmail.setText("");
//            et_modifyUserPhone.setText("");
//
//        } catch (SQLiteConstraintException sce) {
//            Snackbar.make(et_modifyUserEmail, R.string.task_registered_error, BaseTransientBottomBar.LENGTH_LONG).show();
//
//        }
//
//    }

    public void goBack(View view) {
        onBackPressed();
    }
}