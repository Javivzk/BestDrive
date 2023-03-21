package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.BestReadContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.model.BestReadModel;
import com.svalero.bestread.view.BestReadView;

public class BestReadPresenter implements BestReadContract.Presenter {

    private BestReadModel model;
    private BestReadView view;

    public BestReadPresenter(BestReadView view) {
        this.model = new BestReadModel();
        this.view = view;
    }

    @Override
    public void loadAllBooks() {

    }

    @Override
    public void loadBookByTitle(String title) {

    }

    @Override
    public void deleteBook(Book book) {

    }
}
