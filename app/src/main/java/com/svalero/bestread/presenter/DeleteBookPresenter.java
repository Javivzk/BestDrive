package com.svalero.bestread.presenter;

import com.svalero.bestread.adapter.BookAdapter;
import com.svalero.bestread.adapter.LibraryAdapter;
import com.svalero.bestread.contract.DeleteBookContract;
import com.svalero.bestread.contract.DeleteLibraryContract;
import com.svalero.bestread.model.DeleteBookModel;
import com.svalero.bestread.model.DeleteLibraryModel;

public class DeleteBookPresenter implements DeleteBookContract.Presenter,
        DeleteBookContract.Model.OnDeleteBookListener {

    private DeleteBookModel model;

    private BookAdapter view;


    public DeleteBookPresenter(BookAdapter view) {
        this.view = view;
        model = new DeleteBookModel();

    }

    @Override
    public void deleteBook(long bookId) {
        model.deleteBook(bookId, this);
    }

    @Override
    public void onDeleteSuccess() {
        view.showMessage("El libro se ha eliminado correctamente");
    }

    @Override
    public void onDeleteError(String message) {
        view.showError("Se ha producido un error al eliminar el libro. Intentelo de nuevo");
    }
}