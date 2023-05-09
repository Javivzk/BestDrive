package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Library;

import java.util.List;

public interface LibraryListContract {

    interface Model {
        interface OnLoadLibrariesListener {
            void onLoadLibrariesSuccess(List<Library>libraries);
            void onLoadLibrariesError(String message);
        }
        void loadAllLibraries(OnLoadLibrariesListener listener);
        Library loadLibrariesByName(String name);
        List<Library> getAll();
        Library getById(long id);
        void insert(Library library);
        boolean deleteLibrary(String name);
        void update(Library library);



    }

    interface View {
        void showLibraries(List<Library> libraries);
        void showMessage(String message);

    }

    interface Presenter {
        void loadAllLibraries();
        void loadLibraryByName(String name);
        void deleteLibrary(Library library);
    }
}
