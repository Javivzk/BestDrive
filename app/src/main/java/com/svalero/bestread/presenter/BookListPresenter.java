package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.BestReadContract;
import com.svalero.bestread.contract.BookListContract;
import com.svalero.bestread.contract.LibraryListContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.model.BestReadModel;
import com.svalero.bestread.model.BookListModel;
import com.svalero.bestread.view.BestReadView;
import com.svalero.bestread.view.BookListView;

import java.util.List;

public class BookListPresenter implements BookListContract.Presenter,
        BookListContract.Model.OnLoadBooksListener{

    private BookListModel model;
    private BookListView view;

    public BookListPresenter(BookListView view) {
        this.view = view;
        this.model = new BookListModel();

    }

    @Override
    public void loadAllBooks() {
        model.loadAllBooks(this);
    }

    @Override
    public void loadBookByTitle(String title) {

    }

    @Override
    public void deleteBook(Book book) {

    }

    @Override
    public void onLoadBooksSuccess(List<Book> books) {
        view.showBooks(books);

    }

    @Override
    public void onLoadBooksError(String message) {
        view.showMessage(message);
    }
}