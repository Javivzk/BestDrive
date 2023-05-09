package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.RegisterLibraryContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.RegisterLibraryModel;
import com.svalero.bestread.view.RegisterLibraryView;

public class RegisterLibraryPresenter implements RegisterLibraryContract.Presenter,
        RegisterLibraryContract.Model.OnRegisterLibraryListener {

    private RegisterLibraryModel model;

    private RegisterLibraryView view;


    public RegisterLibraryPresenter(RegisterLibraryView view) {
        this.view = view;
        model = new RegisterLibraryModel();

    }

    @Override
    public void registerLibrary(Library library) {
        model.registerLibrary(library, this);
    }

    @Override
    public void onRegisterLibrarySuccess(Library library) {
        view.showMessage("La libreria " + library.getId() + " se ha registrado correctamente");
    }

    @Override
    public void onRegisterLibraryError(String message) {
        view.showError("Se ha producido un error al registrar la libreria. Intentelo de nuevo");
    }
}
