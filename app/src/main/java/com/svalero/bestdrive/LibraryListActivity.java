package com.svalero.bestdrive;
import static com.svalero.bestdrive.db.Constants.DATABASE_NAME;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.bestdrive.adapter.LibraryAdapter;
import com.svalero.bestdrive.db.AppDatabase;
import com.svalero.bestdrive.domain.Library;

import java.util.ArrayList;
import java.util.List;

public class LibraryListActivity extends AppCompatActivity  {

    private List<Library> libraryList;
    private LibraryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_list);

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

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        libraryList.clear();
        libraryList.addAll(db.libraryDao().getAll());
        adapter.notifyDataSetChanged();
    }
}