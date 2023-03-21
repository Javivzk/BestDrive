package com.svalero.bestread.view;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.bestread.R;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.User;

public class RegisterUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
    }

    public void register(View view){
        EditText etName = findViewById(R.id.edit_text_name);
        EditText etLastName = findViewById(R.id.edit_text_last_name);
        EditText etUsername = findViewById(R.id.edit_text_username);
        EditText etPassword = findViewById(R.id.edit_text_password);
        EditText etPhone = findViewById(R.id.edit_text_phone);
        EditText etEmail = findViewById(R.id.edit_text_email);

        String name = etName.getText().toString();
        String lastName = etLastName.getText().toString();
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        String phone = etPhone.getText().toString();
        String email = etEmail.getText().toString();


        User user = new User(username, password, name, lastName,email, phone);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        try {
            db.userDao().insert(user);

            Snackbar.make(etName, R.string.task_registered_user, BaseTransientBottomBar.LENGTH_LONG).show();
            etName.setText("");
            etLastName.setText("");
            etUsername.setText("");
            etPassword.setText("");
            etPhone.setText("");
            etEmail.setText("");

        } catch (SQLiteConstraintException sce) {
            Snackbar.make(etName, R.string.task_registered_error, BaseTransientBottomBar.LENGTH_LONG).show();
            //Toast.makeText(this, R.string.task_registered_error, Toast.LENGTH_LONG).show();
        }
    }

    public void back(View view){
        onBackPressed();
    }

}