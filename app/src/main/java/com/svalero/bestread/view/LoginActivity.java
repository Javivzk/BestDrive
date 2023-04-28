package com.svalero.bestread.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.bestread.R;

public class LoginActivity extends AppCompatActivity {

    public static String currentUser;

    private EditText etUserName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

//    public void login(View view){
//
//        etUserName = findViewById(R.id.etUserName);
//        etPassword = findViewById(R.id.etPassword);
//
//        String username = etUserName.getText().toString();
//        String password = etPassword.getText().toString();
//
//        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
//                .allowMainThreadQueries().build();
//
//        User user = db.userDao().login(username, password);
//
//        if(user != null) {
//            currentUser = user.getUserName();
//            Intent intent = new Intent(this, BestReadView.class);
//            startActivity(intent);
//        }
//        else {
//            Snackbar.make(etUserName,R.string.task_registered_error, BaseTransientBottomBar.LENGTH_LONG).show();
//        }
//
//    }


    public void registerUser(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterUserView.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        currentUser = "";
    }
}