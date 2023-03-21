package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.RegisterBookContract;
import com.svalero.bestread.contract.RegisterLibraryContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.RegisterBookModel;
import com.svalero.bestread.model.RegisterLibraryModel;
import com.svalero.bestread.view.RegisterBookView;
import com.svalero.bestread.view.RegisterLibraryView;

public class RegisterBookPresenter implements RegisterBookContract.Presenter {

    private RegisterBookModel model;

    private RegisterBookView view;

    public RegisterBookPresenter(RegisterBookView view) {
        this.view = view;
        model = new RegisterBookModel(view.getApplicationContext());

    }

    @Override
    public void registerBook(Book book) {
        boolean done = model.registerBook(book);
        if (done) {
            view.showMessage("Tarea registrada correctamente");
            view.resetForm();
        }else {
            view.showError("Se ha producido un error al registrar la libreria. Comprueba que los datos son correctos");
        }
    }
}
