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
import com.svalero.bestread.contract.RegisterBookContract;
import com.svalero.bestread.contract.RegisterUserContract;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.User;
import com.svalero.bestread.presenter.RegisterBookPresenter;
import com.svalero.bestread.presenter.RegisterUserPresenter;

public class RegisterUserView extends AppCompatActivity implements RegisterUserContract.View{

    private RegisterUserPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        presenter = new RegisterUserPresenter(this);

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
        presenter.registerUser(user);

    }

    public void back(View view){
        onBackPressed();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.edit_text_name)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.edit_text_name)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.edit_text_name)).setText("");
        ((EditText) findViewById(R.id.edit_text_last_name)).setText("");
        ((EditText) findViewById(R.id.edit_text_username)).setText("");
        ((EditText) findViewById(R.id.edit_text_password)).setText("");
        ((EditText) findViewById(R.id.edit_text_phone)).setText("");
        ((EditText) findViewById(R.id.edit_text_email)).setText("");
        ((EditText) findViewById(R.id.edit_text_username)).requestFocus();
    }
}