package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;

public interface ModifyBookContract {

    interface Model {
        interface OnModifyBookListener {
            void onModifyBookSuccess(Book book);
            void onModifyBookError(String message);
        }
        void modifyBook (Book book, OnModifyBookListener listener, long bookId);
    }

    interface View {
        void showError(String message);

        void showMessage(String message);

    }

    interface Presenter {
        void modifyBook(Book book, long bookId);
    }
}
