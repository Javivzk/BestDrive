package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.contract.LibraryDetailsContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.BookDetailsModel;
import com.svalero.bestread.model.LibraryDetailsModel;
import com.svalero.bestread.view.BookDetailsView;
import com.svalero.bestread.view.LibraryDetailsView;

public class BookDetailsPresenter implements BookDetailsContract.Presenter {

    private BookDetailsModel model;
    private BookDetailsView view;

    public BookDetailsPresenter(BookDetailsView view) {
        model = new BookDetailsModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loadBook(String title) {
        Book book = model.getBook(title);
        view.showBook(book);
    }
}