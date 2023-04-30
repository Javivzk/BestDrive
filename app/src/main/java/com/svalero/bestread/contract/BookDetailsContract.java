package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;

import java.util.List;

public interface BookDetailsContract {

    interface Model {
        interface OnDetailBookListener {
            void onDetailBookSuccess(Book book);
            void onDetailBookError(String message);
        }
        void loadBook(BookDetailsContract.Model.OnDetailBookListener listener, long bookId);
    }

    interface View {
        void showBook(Book book);
        void showMessage(String message);
    }

    interface Presenter {
        void loadBook(long bookId);
        void deleteBook(Book book);

    }
}
