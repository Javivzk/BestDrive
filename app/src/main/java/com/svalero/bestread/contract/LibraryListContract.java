package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

import java.util.List;

public interface LibraryListContract {

    interface Model {
        List<Library> loadAllLibraries();
        Library getByName(String name);
        List<Library> getAll();
        Library getById(long id);
        void deleteByName(String name);
        void insert(Library library);
        void delete(Library library);
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
