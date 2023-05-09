package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.BookDetailsModel;
import com.svalero.bestread.view.BookDetailsView;

public class BookDetailsPresenter implements BookDetailsContract.Presenter, BookDetailsContract.Model.OnDetailBookListener {

    private BookDetailsContract.View view;
    private BookDetailsContract.Model model;

    public BookDetailsPresenter(BookDetailsContract.View view) {
        this.view = view;
        model = new BookDetailsModel();
    }

    @Override
    public void loadBook(long bookId) {
        model.loadBook(this, bookId);
    }

    @Override
    public void onDetailBookSuccess(Book book) {
        if (view != null) {
            view.showBook(book);
        }
    }

    @Override
    public void onDetailBookError(String message) {
        if (view != null) {
            view.showError(message);
        }
    }

}
