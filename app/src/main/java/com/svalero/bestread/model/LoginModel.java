package com.svalero.bestread.model;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.util.Log;

import com.svalero.bestread.R;
import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.LoginContract;
import com.svalero.bestread.domain.JwtRequest;
import com.svalero.bestread.domain.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginModel implements LoginContract.Model {


    @Override
    public void loginUser(OnLoginListener listener, String username, String password) {
        BestReadApiInterface bestReadApiInterface = BestReadApi.buildInstance();
        Call<User> callUser = bestReadApiInterface.getUserLogin(username, password);
        callUser.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                Log.i("user", "llamada desde modelo ok -> " + response.code());
                listener.onLoginSuccess(user);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onLoginError(message);
            }
        });
    }
}
