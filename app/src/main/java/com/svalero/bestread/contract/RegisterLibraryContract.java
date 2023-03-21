package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Library;

public interface RegisterLibraryContract {

    interface Model {
        boolean registerLibrary (Library library);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
        void resetForm();

    }

    interface Presenter {
        void registerLibrary(Library library);
    }
}
