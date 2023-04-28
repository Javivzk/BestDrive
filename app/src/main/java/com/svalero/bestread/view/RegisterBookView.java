package com.svalero.bestread.view;


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
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.presenter.RegisterBookPresenter;
import com.svalero.bestread.presenter.RegisterLibraryPresenter;

import java.time.LocalDate;

public class RegisterBookView extends AppCompatActivity implements RegisterBookContract.View{

    private RegisterBookPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_book);

        presenter = new RegisterBookPresenter(this);

    }

    public void saveButton(View view) {
        EditText etCode = findViewById(R.id.edit_text_code);
        EditText etTitle = findViewById(R.id.edit_text_title);
        EditText etAuthor = findViewById(R.id.edit_text_author);
        EditText etYear = findViewById(R.id.edit_text_year);
        EditText etGenre = findViewById(R.id.edit_text_genre);
        EditText etDescription = findViewById(R.id.edit_text_description);
        EditText etPages = findViewById(R.id.edit_text_pages);
        EditText etPrice = findViewById(R.id.edit_text_price);
        EditText etHasStock = findViewById(R.id.edit_text_hasStock);


        String code = etCode.getText().toString();
        String title = etTitle.getText().toString();
        String author = etAuthor.getText().toString();
        String year = etYear.getText().toString();
        String genre = etGenre.getText().toString();
        String description = etDescription.getText().toString();
        int pages = Integer.parseInt(etPages.getText().toString());
        int price = Integer.parseInt(etPrice.getText().toString());
        boolean hasStock = Boolean.parseBoolean(etHasStock.getText().toString());



        Book book = new Book(code,title, author,year, genre, description, pages, price, hasStock);
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
        ((EditText) findViewById(R.id.edit_text_code)).setText("");
        ((EditText) findViewById(R.id.edit_text_title)).setText("");
        ((EditText) findViewById(R.id.edit_text_author)).setText("");
        ((EditText) findViewById(R.id.edit_text_year)).setText("");
        ((EditText) findViewById(R.id.edit_text_genre)).setText("");
        ((EditText) findViewById(R.id.edit_text_description)).setText("");
        ((EditText) findViewById(R.id.edit_text_pages)).setText("");
        ((EditText) findViewById(R.id.edit_text_price)).setText("");
        ((EditText) findViewById(R.id.edit_text_hasStock)).setText("");

        ((EditText) findViewById(R.id.edit_text_title)).requestFocus();
    }
}
