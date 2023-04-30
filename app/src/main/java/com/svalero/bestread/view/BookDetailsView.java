package com.svalero.bestread.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.bestread.R;
import com.svalero.bestread.adapter.BookAdapter;
import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.presenter.BookDetailsPresenter;

public class BookDetailsView extends AppCompatActivity implements BookDetailsContract.View {

    private BookDetailsPresenter presenter;
    private BookAdapter bookAdapter;
    long bookId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        presenter = new BookDetailsPresenter(this);

        Intent intent = getIntent();
        bookId = intent.getLongExtra("bookId", 0);
        if (bookId == 0)
            return;

        presenter.loadBook(bookId);
    }


//    public void modifyBook(View view) {
//        Intent intent = getIntent();
//
//        String title = intent.getStringExtra("title");
//        if (title == null)
//            return;
//
//        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
//                .allowMainThreadQueries().build();
//
//        Book newBook = db.bookDao().getByTitle(title);
//
//        EditText authorField = findViewById(R.id.et_book_author);
//        EditText descriptionField = findViewById(R.id.et_book_description);
//
//        newBook.setAuthor(authorField.getText().toString());
//        newBook.setDescription(descriptionField.getText().toString());
//
//
//        try {
//            db.bookDao().getByTitle(newBook.getTitle());
//            db.bookDao().update(newBook);
//            Toast.makeText(this, R.string.book_modified_message, Toast.LENGTH_LONG).show();
//            onBackPressed();
//        } catch (SQLiteConstraintException sce) {
//            Toast.makeText(this, "El libro no existe", Toast.LENGTH_LONG).show();
//
//        }
//    }


    @Override
    public void showBook(Book book) {
        EditText etCode = findViewById(R.id.et_book_code);
        EditText etTitle = findViewById(R.id.et_book_title);
        EditText etAuthor = findViewById(R.id.et_book_author);
        EditText etYear = findViewById(R.id.et_book_year);
        EditText etGenre = findViewById(R.id.et_book_genre);
        EditText etDescription = findViewById(R.id.et_book_description);
        EditText etPages = findViewById(R.id.et_book_pages);
        EditText etPrice = findViewById(R.id.et_book_price);
        EditText etHasStock = findViewById(R.id.et_book_hasStock);

        etCode.setText(book.getCode());
        etTitle.setText(book.getTitle());
        etAuthor.setText(book.getAuthor());
        etYear.setText(book.getYear());
        etGenre.setText(book.getGenre());
        etDescription.setText(book.getDescription());
        etPages.setText(book.getPages());
        etPrice.setText(String.valueOf(book.getPrice()));
        etHasStock.setText(String.valueOf(book.isHasStock()));
    }

    @Override
    public void showMessage(String message) {

    }
}