package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.BookDetailsModel;
import com.svalero.bestread.view.BookDetailsView;

public class BookDetailsPresenter implements BookDetailsContract.Presenter,
BookDetailsContract.Model.OnDetailBookListener{

    private BookDetailsModel model;
    private BookDetailsView view;

    public BookDetailsPresenter(BookDetailsView view) {
        model = new BookDetailsModel(view.getApplicationContext());
        this.view = view;
    }

    @Override
    public void loadBook(long bookId) {
        model.loadBook(this,bookId);
    }

    @Override
    public void onDetailBookSuccess(Book book) {
        view.showBook(book);
    }

    @Override
    public void onDetailBookError(String message) {
        view.showMessage(message);
    }

    @Override
    public void deleteBook(Book book) {

    }
}