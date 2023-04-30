package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

public interface LibraryDetailsContract {

    interface Model {
        interface OnDetailLibraryListener {
            void onDetailLibrarySuccess(Library library);
            void onDetailLibraryError(String message);
        }
        void loadLibrary(LibraryDetailsContract.Model.OnDetailLibraryListener listener, long libraryId);
    }

    interface View {
        void showLibrary(Library library);
        void showMessage(String message);
    }

    interface Presenter {
        void loadLibrary(long libraryId);
        void deleteLibrary(Library library);

    }
}
