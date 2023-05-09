package com.svalero.bestread.model;

import android.content.Context;
import android.util.Log;

import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.contract.LibraryDetailsContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryDetailsModel implements LibraryDetailsContract.Model {


    @Override
    public void loadLibrary(OnDetailLibraryListener listener, long libraryId) {
        BestReadApiInterface bestReadApiInterface = BestReadApi.buildInstance();
        Call<Library> callLibrary = bestReadApiInterface.getLibrary(libraryId);
        callLibrary.enqueue(new Callback<Library>() {
            @Override
            public void onResponse(Call<Library> call, Response<Library> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Library library = response.body();
                    listener.onDetailLibrarySuccess(library);
                } else {
                    listener.onDetailLibraryError("Error al recuperar la biblioteca");
                }
            }

            @Override
            public void onFailure(Call<Library> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onDetailLibraryError(message);
            }
        });
    }

}
