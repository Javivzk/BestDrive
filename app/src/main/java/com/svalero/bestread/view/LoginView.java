package com.svalero.bestread.view;

import static com.svalero.bestread.api.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.bestread.R;
import com.svalero.bestread.db.BestReadDatabase;
import com.svalero.bestread.domain.User;

public class LoginView extends AppCompatActivity {

    public static String currentUser;

    private EditText etUserName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


    }

    public void login(View view){

        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);

        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        final BestReadDatabase db = Room.databaseBuilder(this, BestReadDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        User user = db.userDao().login(username, password);

        if(user != null) {
            currentUser = user.getUsername();
            Intent intent = new Intent(this, BestReadView.class);
            startActivity(intent);
        }
        else {
            Snackbar.make(etUserName,R.string.task_registered_error, BaseTransientBottomBar.LENGTH_LONG).show();
        }

    }


    public void registerUser(View view){
        Intent intent = new Intent(LoginView.this, RegisterUserView.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        currentUser = "";
    }
}