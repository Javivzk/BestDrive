package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.RegisterBookContract;
import com.svalero.bestread.contract.RegisterLibraryContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.RegisterBookModel;
import com.svalero.bestread.view.RegisterBookView;

public class RegisterBookPresenter implements RegisterBookContract.Presenter,
        RegisterBookContract.Model.OnRegisterBookListener {

    private RegisterBookModel model;

    private RegisterBookView view;

    public RegisterBookPresenter(RegisterBookView view) {
        this.view = view;
        model = new RegisterBookModel();

    }

    @Override
    public void registerBook(Book book) {
        model.registerBook(book, this);
    }

    @Override
    public void onRegisterBookSuccess(Book book) {
        view.showMessage("El libro " + book.getId() + " se ha registrado correctamente");
    }

    @Override
    public void onRegisterBookError(String message) {
        view.showError("Se ha producido un error al registrar el libro. Intentelo de nuevo");
    }
}
