package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;

import java.util.List;

public interface BookDetailsContract {

    interface Model {
        interface OnDetailBookListener {
            void onDetailBookSuccess(Book book);
            void onDetailBookError(String message);
        }
        void loadBook(OnDetailBookListener listener, long bookId);

    }

    interface View {
        void showBook(Book book);
        void showError(String message);

        void showMessage(String message);
    }

    interface Presenter {
        void loadBook(long bookId);
    }




}
