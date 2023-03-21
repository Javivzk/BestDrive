package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.RegisterBookContract;
import com.svalero.bestread.contract.RegisterUserContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.User;
import com.svalero.bestread.model.RegisterBookModel;
import com.svalero.bestread.model.RegisterUserModel;
import com.svalero.bestread.view.RegisterBookView;
import com.svalero.bestread.view.RegisterUserView;

public class RegisterUserPresenter implements RegisterUserContract.Presenter {

    private RegisterUserModel model;

    private RegisterUserView view;

    public RegisterUserPresenter(RegisterUserView view) {
        this.view = view;
        model = new RegisterUserModel(view.getApplicationContext());

    }

    @Override
    public void registerUser(User user) {
        boolean done = model.registerUser(user);
        if (done) {
            view.showMessage("Tarea registrada correctamente");
            view.resetForm();
        }else {
            view.showError("Se ha producido un error al registrar la libreria. Comprueba que los datos son correctos");
        }
    }
}