package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.LibraryListContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.LibraryListModel;
import com.svalero.bestread.view.LibraryMapView;

import java.util.List;

public class LibraryMapPresenter implements LibraryListContract.Presenter, LibraryListContract.Model.OnLoadLibrariesListener {

    private LibraryListModel model;
    private LibraryMapView view;

    public LibraryMapPresenter(LibraryMapView view) {
        this.model = new LibraryListModel();
        this.view = view;
    }

    @Override
    public void onLoadLibrariesSuccess(List<Library> librariesList) {
        view.showLibraries(librariesList);
    }

    @Override
    public void onLoadLibrariesError(String message) {

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
}
