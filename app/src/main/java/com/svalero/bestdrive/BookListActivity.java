package com.svalero.bestdrive;

import static com.svalero.bestdrive.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.bestdrive.adapter.BookAdapter;
import com.svalero.bestdrive.adapter.LibraryAdapter;
import com.svalero.bestdrive.db.AppDatabase;
import com.svalero.bestdrive.domain.Book;

import java.util.ArrayList;
import java.util.List;

public class BookListActivity extends AppCompatActivity {

    private List<Book> bookList;
    private BookAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        bookList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.book_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new BookAdapter(this, bookList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        bookList.clear();
        bookList.addAll(db.bookDao().getAll());
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.booksbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.register_book) {
        Intent intent = new Intent(this, RegisterBookActivity.class);
        startActivity(intent);
        return true;
        }else if (item.getItemId() == R.id.view_settings) {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        }else if (item.getItemId() == R.id.view_profile) {
            Intent intent = new Intent(this, ViewProfileActivity.class);
            startActivity(intent);

        }
        return false;
    }
}