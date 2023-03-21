package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.LibraryDetailsContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.LibraryDetailsModel;
import com.svalero.bestread.view.LibraryDetailsView;

public class LibraryDetailsPresenter implements LibraryDetailsContract.Presenter {

    private LibraryDetailsModel model;
    private LibraryDetailsView view;

    public LibraryDetailsPresenter(LibraryDetailsView view) {
        model = new LibraryDetailsModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loadLibrary(String name) {
        Library library = model.getLibrary(name);
        view.showLibrary(library);
    }
}
