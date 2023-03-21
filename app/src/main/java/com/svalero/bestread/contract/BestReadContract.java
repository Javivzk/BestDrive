package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;

import java.util.List;

public interface BestReadContract {

    interface Model {
        List<Book> loadAllBooks();
        Book getByTitle(String title);
        List<Book> getAll();
        Book getById(long id);
        void deleteByTitle(String title);
        void insert(Book book);
        void delete(Book book);
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
