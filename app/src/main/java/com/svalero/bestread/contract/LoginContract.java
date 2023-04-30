package com.svalero.bestread.contract;

import com.svalero.bestread.domain.User;

public interface LoginContract {

    interface Model {
        interface OnLoginListener{
            void onLoginSuccess(User user);
            void onLoginError(String message);
        }
        void loginUser(OnLoginListener listener, String username, String password);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
    }

    interface Presenter {
        void login(String username, String password);
    }
}
