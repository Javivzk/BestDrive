package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

public interface RegisterBookContract {

    interface Model {
        boolean registerBook (Book book);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
        void resetForm();

    }

    interface Presenter {
        void registerBook(Book book);
    }
}
