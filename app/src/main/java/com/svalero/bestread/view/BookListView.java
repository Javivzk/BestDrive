package com.svalero.bestread.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.bestread.R;
import com.svalero.bestread.adapter.BookAdapter;
import com.svalero.bestread.contract.BookListContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.presenter.BookListPresenter;

import java.util.ArrayList;
import java.util.List;

public class BookListView extends AppCompatActivity implements BookListContract.View {

    private List<Book> bookList;
    private BookAdapter adapter;
    private BookListPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);

        presenter = new BookListPresenter(this);


        initializeRecyclerView();
    }

    private void initializeRecyclerView() {
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

        presenter.loadAllBooks();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.booksbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.register_book) {
        Intent intent = new Intent(this, RegisterBookView.class);
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

    @Override
    public void showBooks(List<Book> books) {
        bookList.clear();
        bookList.addAll(books);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}