package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Library;

public interface DeleteLibraryContract {

    interface Model {
        interface OnDeleteLibraryListener{
            void onDeleteSuccess();
            void onDeleteError(String message);
        }
        void deleteLibrary(long libraryId, OnDeleteLibraryListener listener);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
    }

    interface Presenter {
        void deleteLibrary(long libraryId);
    }
}
