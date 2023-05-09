package com.svalero.bestread.contract;

public interface DeleteBookContract {

    interface Model {
        interface OnDeleteBookListener{
            void onDeleteSuccess();
            void onDeleteError(String message);
        }
        void deleteBook(long bookId, OnDeleteBookListener listener);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
    }

    interface Presenter {
        void deleteBook(long bookId);
    }
}
