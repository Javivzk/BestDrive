package com.svalero.bestread.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import com.svalero.bestread.R;
import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.contract.ModifyBookContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.presenter.BookDetailsPresenter;
import com.svalero.bestread.presenter.ModifyBookPresenter;

public class BookDetailsView extends AppCompatActivity implements BookDetailsContract.View, ModifyBookContract.View {

    private BookDetailsContract.Presenter presenter;
    private ModifyBookContract.Presenter modifyPresenter;
    private long bookId;
    private Book loadedBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        presenter = new BookDetailsPresenter(this);
        modifyPresenter = new ModifyBookPresenter(this);


        SharedPreferences preferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        String username = preferences.getString("username","");
        String password = preferences.getString("password", "");

        Intent intent = getIntent();
        bookId = intent.getLongExtra("bookId", 0);

        Toast.makeText(this, "Received bookId: " + bookId, Toast.LENGTH_SHORT).show();

        Log.d("BookDetailsView", "Book id: " + bookId);

        presenter.loadBook(bookId);
    }


    public void modifyBook(View view) {
        Log.d("BookDetailsView", "modifyBook clicked");

        Intent intent = getIntent();

        bookId = intent.getLongExtra("bookId",0);
        if (bookId == 0) {
            return;
        }



        EditText codeField = findViewById(R.id.et_book_code);
        EditText yearField = findViewById(R.id.et_book_year);
        EditText genreField = findViewById(R.id.et_book_genre);
        EditText authorField = findViewById(R.id.et_book_author);
        EditText descriptionField = findViewById(R.id.et_book_description);
        EditText titleField = findViewById(R.id.et_book_title);

        Book updatedBook = new Book();

        updatedBook.setCode(codeField.getText().toString());
        updatedBook.setGenre(genreField.getText().toString());
        updatedBook.setYear(yearField.getText().toString());
        updatedBook.setAuthor(authorField.getText().toString());
        updatedBook.setDescription(descriptionField.getText().toString());
        updatedBook.setTitle(titleField.getText().toString());


        modifyPresenter.modifyBook(updatedBook, bookId);
    }


    @Override
    public void showBook(Book book) {
        EditText etCode = findViewById(R.id.et_book_code);
        EditText etTitle = findViewById(R.id.et_book_title);
        EditText etAuthor = findViewById(R.id.et_book_author);
        EditText etYear = findViewById(R.id.et_book_year);
        EditText etGenre = findViewById(R.id.et_book_genre);
        EditText etDescription = findViewById(R.id.et_book_description);

        etCode.setText(book.getCode());
        etTitle.setText(book.getTitle());
        etAuthor.setText(book.getAuthor());
        etYear.setText(book.getYear());
        etGenre.setText(book.getGenre());
        etDescription.setText(book.getDescription());

        loadedBook = book;
    }
    public void goBackButton(View view) {
        onBackPressed();
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
