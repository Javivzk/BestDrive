package com.svalero.bestread.view;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.bestread.R;
import com.svalero.bestread.contract.RegisterBookContract;
import com.svalero.bestread.contract.RegisterLibraryContract;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.presenter.RegisterBookPresenter;
import com.svalero.bestread.presenter.RegisterLibraryPresenter;

public class RegisterBookView extends AppCompatActivity implements RegisterBookContract.View{

    private RegisterBookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_book);

        presenter = new RegisterBookPresenter(this);

    }

    public void saveButton(View view) {
        EditText etTitle = findViewById(R.id.edit_text_title);
        EditText etAuthor = findViewById(R.id.edit_text_author);
        EditText etDescription = findViewById(R.id.edit_text_description);


        String title = etTitle.getText().toString();
        String author = etAuthor.getText().toString();
        String description = etDescription.getText().toString();



        Book book = new Book(title, author, description);
        presenter.registerBook(book);
    }


    public void goBackButton(View view) {
        onBackPressed();
    }


    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.edit_text_title)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.edit_text_title)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.edit_text_title)).setText("");
        ((EditText) findViewById(R.id.edit_text_author)).setText("");
        ((EditText) findViewById(R.id.edit_text_description)).setText("");
        ((EditText) findViewById(R.id.edit_text_title)).requestFocus();
    }
}
