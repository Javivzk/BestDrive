package com.svalero.bestread.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;

import com.svalero.bestread.R;
import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.LoginContract;
import com.svalero.bestread.domain.JwtRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model {

    private Context context;
    private LoginModel(Context context) {
        this.context = context;
    }

    @Override
    public void login(String username, String password, OnLoginListener listener) {
        try {
            BestReadApiInterface bestReadApi = BestReadApi.buildInstance();
            Call<Void> callLogin = bestReadApi.login(new JwtRequest());
            callLogin.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    SharedPreferences preferences = context.getSharedPreferences(
                            context.getString(R.string.app_name), Context.MODE_PRIVATE);
                    preferences.edit()
                        .putString("username",username)
                        .putString("password",password)
                        .apply();
                    listener.onLoginSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error en el login";
                    listener.onLoginError(message);
                }
            });
        }catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
