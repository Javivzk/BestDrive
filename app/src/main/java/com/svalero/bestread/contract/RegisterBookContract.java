package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

public interface RegisterBookContract {

    interface Model {
        interface OnRegisterBookListener {
            void onRegisterBookSuccess(Book book);
            void onRegisterBookError(String message);
        }
        void registerBook (Book book, OnRegisterBookListener listener);
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
