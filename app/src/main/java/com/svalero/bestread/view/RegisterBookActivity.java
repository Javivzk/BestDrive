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
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Book;

public class RegisterBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_book);
    }

    public void saveButton(View view) {
        EditText etTitle = findViewById(R.id.edit_text_title);
        EditText etAuthor = findViewById(R.id.edit_text_author);
        EditText etDescription = findViewById(R.id.edit_text_description);


        String title = etTitle.getText().toString();
        String author = etAuthor.getText().toString();
        String description = etDescription.getText().toString();



        Book book = new Book(title, author, description);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        try {
            db.bookDao().insert(book);

            Toast.makeText(this, R.string.task_book_registered_message, Toast.LENGTH_LONG).show();
            etTitle.setText("");
            etAuthor.setText("");
            etDescription.setText("");
            etTitle.requestFocus();
        } catch (SQLiteConstraintException sce) {
            Snackbar.make(etTitle, R.string.task_registered_error, BaseTransientBottomBar.LENGTH_LONG).show();
            //Toast.makeText(this, R.string.task_registered_error, Toast.LENGTH_LONG).show();
        }
    }


    public void goBackButton(View view) {
        onBackPressed();
    }


}
