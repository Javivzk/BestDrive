package com.svalero.bestread.contract;

import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.User;

public interface RegisterUserContract {

    interface Model {
        boolean registerUser (User user);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
        void resetForm();

    }

    interface Presenter {
        void registerUser(User user);
    }
}
