package com.svalero.bestread.contract;

public interface LoginContract {

    interface Model {
        interface OnLoginListener{
            void onLoginSuccess();
            void onLoginError(String message);
        }
        void login(String username, String password, LoginContract.Model.OnLoginListener listener);
    }

    interface View {
        void showError(String errorMessage);
        void showMessage(String message);
    }

    interface Presenter {
        void login(String username, String password);
    }
}
