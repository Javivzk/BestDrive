package com.svalero.bestread.model;


import android.database.sqlite.SQLiteConstraintException;

import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.RegisterLibraryContract;
import com.svalero.bestread.domain.Library;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterLibraryModel implements RegisterLibraryContract.Model {

    @Override
    public void registerLibrary(Library library, OnRegisterLibraryListener listener) {
        try {
            BestReadApiInterface bestReadApi = BestReadApi.buildInstance();
            Call<Library> callLibraries = bestReadApi.addLibrary(library);
            callLibraries.enqueue(new Callback<Library>() {
                @Override
                public void onResponse(Call<Library> call, Response<Library> response) {
                    Library library = response.body();
                    listener.onRegisterLibrarySuccess(library);

                }

                @Override
                public void onFailure(Call<Library> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error invocando a la operacion";
                    listener.onRegisterLibraryError(message);

                }
            });
        }catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}
