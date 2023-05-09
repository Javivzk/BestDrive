package com.svalero.bestread.presenter;

import com.svalero.bestread.contract.ModifyLibraryContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.ModifyLibraryModel;
import com.svalero.bestread.view.LibraryDetailsView;
import com.svalero.bestread.view.RegisterLibraryView;

public class ModifyLibraryPresenter implements ModifyLibraryContract.Presenter, ModifyLibraryContract.Model.OnModifyLibraryListener {
    private ModifyLibraryModel model;
    private LibraryDetailsView view;

    public ModifyLibraryPresenter(LibraryDetailsView view){
        model = new ModifyLibraryModel();
        this.view = view;
    }

    @Override
    public void modifyLibrary(Library library, long libraryId) {
        model.modifyLibrary(library, this,libraryId);
    }

    @Override
    public void onModifyLibrarySuccess(Library library) {
        if (library != null) {
            view.showMessage("La biblioteca: " + library.getName() + " se ha editado correctamente!");
        } else {
            view.showError("El objeto Biblioteca recibido es nulo.");
        }
    }


    @Override
    public void onModifyLibraryError(String message) {
        view.showError(message);
    }

}
