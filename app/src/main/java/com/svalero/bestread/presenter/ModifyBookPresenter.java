package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.ModifyBookContract;
import com.svalero.bestread.contract.ModifyLibraryContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.ModifyBookModel;
import com.svalero.bestread.model.ModifyLibraryModel;
import com.svalero.bestread.view.BookDetailsView;
import com.svalero.bestread.view.RegisterBookView;
import com.svalero.bestread.view.RegisterLibraryView;

public class ModifyBookPresenter implements ModifyBookContract.Presenter, ModifyBookContract.Model.OnModifyBookListener {
    private ModifyBookModel model;
    private BookDetailsView view;

    public ModifyBookPresenter(BookDetailsView view){
        model = new ModifyBookModel();
        this.view = view;
    }

    @Override
    public void modifyBook(Book book, long bookId) {
        model.modifyBook(book, this,bookId);
    }

    @Override
    public void onModifyBookSuccess(Book book) {
        if (book != null) {
            view.showMessage("El libro: " + book.getTitle() + " se ha editado correctamente!");
        } else {
            view.showError("El objeto Book recibido es nulo.");
        }
    }


    @Override
    public void onModifyBookError(String message) {
        view.showError(message);
    }

}
