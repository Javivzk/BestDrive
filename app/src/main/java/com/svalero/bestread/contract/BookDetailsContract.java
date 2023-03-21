package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

public interface BookDetailsContract {

    interface Model {
        Book getBook (String title);
    }

    interface View {
        void showBook (Book book);

    }

    interface Presenter {
        void loadBook(String title);
    }
}
