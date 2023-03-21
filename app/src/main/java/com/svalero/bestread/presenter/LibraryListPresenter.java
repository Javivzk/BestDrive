package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.LibraryListContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.BookListModel;
import com.svalero.bestread.model.LibraryListModel;
import com.svalero.bestread.view.BookListView;
import com.svalero.bestread.view.LibraryListView;

import java.util.List;

public class LibraryListPresenter implements LibraryListContract.Presenter {

    private LibraryListModel model;
    private LibraryListView view;

    public LibraryListPresenter(LibraryListView view) {
        this.view = view;
        this.model = new LibraryListModel(view.getApplicationContext());
    }

    @Override
    public void loadAllLibraries() {
        List<Library> libraries = model.loadAllLibraries();
        view.showLibraries(libraries);
    }

    @Override
    public void loadLibraryByName(String name) {

    }

    @Override
    public void deleteLibrary(Library library) {

    }
}
