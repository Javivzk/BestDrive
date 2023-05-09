package com.svalero.bestread.presenter;

import com.svalero.bestread.adapter.LibraryAdapter;
import com.svalero.bestread.contract.DeleteLibraryContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.model.DeleteLibraryModel;

public class DeleteLibraryPresenter implements DeleteLibraryContract.Presenter,
        DeleteLibraryContract.Model.OnDeleteLibraryListener {

    private DeleteLibraryModel model;

    private LibraryAdapter view;


    public DeleteLibraryPresenter(LibraryAdapter view) {
        this.view = view;
        model = new DeleteLibraryModel();

    }

    @Override
    public void deleteLibrary(long libraryId) {
        model.deleteLibrary(libraryId, this);
    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("La libreria se ha eliminado correctamente");
    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar la libreria. Intentelo de nuevo");
    }
}