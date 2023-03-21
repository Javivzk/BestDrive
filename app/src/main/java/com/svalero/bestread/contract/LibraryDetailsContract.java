package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Library;

public interface LibraryDetailsContract {

    interface Model {
        Library getLibrary (String name);
    }

    interface View {
        void showLibrary (Library library);

    }

    interface Presenter {
        void loadLibrary(String name);
    }
}
