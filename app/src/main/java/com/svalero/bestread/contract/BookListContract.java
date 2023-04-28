package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

import java.util.List;

public interface BookListContract {

    interface Model {
        interface OnLoadBooksListener {
            void onLoadBooksSuccess(List<Book>books);
            void onLoadBooksError(String message);
        }
        void loadAllBooks(OnLoadBooksListener listener);
        Book loadBookByTitle(String title);
        List<Book> getAll();
        Book getById(long id);
        void insert(Book book);
        boolean deleteBook(String title);
        void update(Book book);



    }

    interface View {
        void showBooks(List<Book> books);
        void showMessage(String message);

    }

    interface Presenter {
        void loadAllBooks();
        void loadBookByTitle(String title);
        void deleteBook(Book book);
    }
}
