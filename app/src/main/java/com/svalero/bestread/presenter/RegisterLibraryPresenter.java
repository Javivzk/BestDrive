package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.RegisterLibraryContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.RegisterLibraryModel;
import com.svalero.bestread.view.RegisterLibraryView;

public class RegisterLibraryPresenter implements RegisterLibraryContract.Presenter {

    private RegisterLibraryModel model;

    private RegisterLibraryView view;

    public RegisterLibraryPresenter(RegisterLibraryView view) {
        this.view = view;
        model = new RegisterLibraryModel(view.getApplicationContext());

    }

    @Override
    public void registerLibrary(Library library) {
        boolean done = model.registerLibrary(library);
        if (done) {
            view.showMessage("Tarea registrada correctamente");
            view.resetForm();
        }else {
            view.showError("Se ha producido un error al registrar la libreria. Comprueba que los datos son correctos");
        }
    }
}
