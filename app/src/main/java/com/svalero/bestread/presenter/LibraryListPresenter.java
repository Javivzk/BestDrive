package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.LibraryListContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.LibraryListModel;
import com.svalero.bestread.view.LibraryListView;

import java.util.List;

public class LibraryListPresenter implements LibraryListContract.Presenter,
        LibraryListContract.Model.OnLoadLibrariesListener {

    private LibraryListModel model;
    private LibraryListView view;

    public LibraryListPresenter(LibraryListView view) {
        this.view = view;
        this.model = new LibraryListModel();
    }

    @Override
    public void loadAllLibraries() {
        model.loadAllLibraries(this);
    }

    @Override
    public void loadLibraryByName(String name) {

    }

    @Override
    public void deleteLibrary(Library library) {

    }

    @Override
    public void onLoadLibrariesSuccess(List<Library> libraries) {
        view.showLibraries(libraries);
    }

    @Override
    public void onLoadLibrariesError(String message) {
        view.showMessage(message);
    }
}
