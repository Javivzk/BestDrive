package com.svalero.bestread.view;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.svalero.bestread.R;
import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.contract.LibraryDetailsContract;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.presenter.BookDetailsPresenter;
import com.svalero.bestread.presenter.LibraryDetailsPresenter;

public class BookDetailsView extends AppCompatActivity implements BookDetailsContract.View {

    private BookDetailsPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_details);

        presenter = new BookDetailsPresenter(this);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        if (title == null)
            return;

        presenter.loadBook(title);
    }


    public void modifyBook(View view) {
        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        if (title == null)
            return;

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        Book newBook = db.bookDao().getByTitle(title);

        EditText authorField = findViewById(R.id.et_book_author);
        EditText descriptionField = findViewById(R.id.et_book_description);

        newBook.setAuthor(authorField.getText().toString());
        newBook.setDescription(descriptionField.getText().toString());


        try {
            db.bookDao().getByTitle(newBook.getTitle());
            db.bookDao().update(newBook);
            Toast.makeText(this, R.string.book_modified_message, Toast.LENGTH_LONG).show();
            onBackPressed();
        } catch (SQLiteConstraintException sce) {
            Toast.makeText(this, "El libro no existe", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    public void showBook(Book book) {
        EditText etTitle = findViewById(R.id.et_book_title);
        EditText etAuthor = findViewById(R.id.et_book_author);
        EditText etDescription = findViewById(R.id.et_book_description);

        etTitle.setText(book.getTitle());
        etAuthor.setText(book.getAuthor());
        etDescription.setText(book.getDescription());
    }
}