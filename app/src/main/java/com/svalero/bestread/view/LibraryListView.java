package com.svalero.bestread.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.svalero.bestread.R;
import com.svalero.bestread.adapter.LibraryAdapter;
import com.svalero.bestread.contract.LibraryListContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.presenter.LibraryListPresenter;

import java.util.ArrayList;
import java.util.List;

public class LibraryListView extends AppCompatActivity implements LibraryListContract.View  {

    private List<Library> libraryList;
    private LibraryAdapter adapter;

    private LibraryListPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_list);

        presenter = new LibraryListPresenter(this);

        initializeRecyclerView();

    }

    private void initializeRecyclerView() {
        libraryList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.library_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new LibraryAdapter(this, libraryList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        presenter.loadAllLibraries();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.librariesbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.register_library) {
            Intent intent = new Intent(this, RegisterLibraryView.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.view_map) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
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
    public void showLibraries(List<Library> libraries) {
        libraryList.clear();
        libraryList.addAll(libraries);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void showMessage(String message) {

    }
}