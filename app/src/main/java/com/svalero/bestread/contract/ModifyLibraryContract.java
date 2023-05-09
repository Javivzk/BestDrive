package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Library;

public interface ModifyLibraryContract {

    interface Model {
        interface OnModifyLibraryListener {
            void onModifyLibrarySuccess(Library library);
            void onModifyLibraryError(String message);
        }
        void modifyLibrary (Library library, OnModifyLibraryListener listener, long libraryId);
    }

    interface View {
        void showError(String message);
        void showMessage(String message);

    }

    interface Presenter {
        void modifyLibrary(Library library,long libraryId);
    }
}
