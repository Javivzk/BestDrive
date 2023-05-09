package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.contract.LibraryDetailsContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.BookDetailsModel;
import com.svalero.bestread.model.LibraryDetailsModel;
import com.svalero.bestread.view.BookDetailsView;
import com.svalero.bestread.view.LibraryDetailsView;

public class LibraryDetailsPresenter implements LibraryDetailsContract.Presenter,
        LibraryDetailsContract.Model.OnDetailLibraryListener {

    private LibraryDetailsModel model;
    private LibraryDetailsView view;

    public LibraryDetailsPresenter(LibraryDetailsView view) {
        model = new LibraryDetailsModel();
        this.view = view;
    }

    @Override
    public void loadLibrary(long libraryId) {
        model.loadLibrary(this, libraryId);
    }


    @Override
    public void onDetailLibrarySuccess(Library library) {
        if (view != null) {
            view.showLibrary(library);
        }
    }


    @Override
    public void onDetailLibraryError(String message) {
        if (view != null) {
            view.showMessage(message);
        }
    }
}