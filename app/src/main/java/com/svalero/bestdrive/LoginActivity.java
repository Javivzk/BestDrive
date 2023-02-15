package com.svalero.bestdrive;

import static com.svalero.bestdrive.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.bestdrive.db.AppDatabase;
import com.svalero.bestdrive.domain.User;

public class LoginActivity extends AppCompatActivity {

    public static String currentUser;

    private EditText etUserName;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUserName = findViewById(R.id.etUserName);
        etPassword = findViewById(R.id.etPassword);
    }

    public void login(View view){

        String username = etUserName.getText().toString();
        String password = etPassword.getText().toString();

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, "users")
                .allowMainThreadQueries().build();

        User user = db.userDao().login(username, password);

        if(user != null) {
            currentUser = user.getUserName();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else {
            Snackbar.make(etUserName,R.string.task_registered_error, BaseTransientBottomBar.LENGTH_LONG).show();
        }

    }


    public void registerUser(View view){
        Intent intent = new Intent(LoginActivity.this, RegisterUserActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        currentUser = "";
    }
}