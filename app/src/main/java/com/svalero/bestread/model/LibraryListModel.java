package com.svalero.bestread.model;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.content.Context;

import androidx.room.Room;

import com.svalero.bestread.contract.LibraryListContract;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Library;

import java.util.List;

public class LibraryListModel implements LibraryListContract.Model {

    private Context context;

    public LibraryListModel(Context context) {
        this.context = context;
    }

    @Override
    public List<Library> loadAllLibraries() {
        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        return db.libraryDao().getAll();
    }

    @Override
    public Library getByName(String name) {
        return null;
    }

    @Override
    public List<Library> getAll() {
        return null;
    }

    @Override
    public Library getById(long id) {
        return null;
    }

    @Override
    public void deleteByName(String name) {

    }

    @Override
    public void insert(Library library) {

    }

    @Override
    public void delete(Library library) {

    }

    @Override
    public void update(Library library) {

    }
}
