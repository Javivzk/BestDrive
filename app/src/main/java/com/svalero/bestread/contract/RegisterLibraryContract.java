package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Library;

public interface RegisterLibraryContract {

    interface Model {
        interface OnRegisterLibraryListener {
            void onRegisterLibrarySuccess(Library library);
            void onRegisterLibraryError(String message);
        }
        void registerLibrary (Library library, OnRegisterLibraryListener listener);
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
