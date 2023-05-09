package com.svalero.bestread.model;

import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.ModifyBookContract;
import com.svalero.bestread.contract.ModifyLibraryContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyLibraryModel implements ModifyLibraryContract.Model {
    @Override
    public void modifyLibrary(Library library, OnModifyLibraryListener listener, long libraryId) {
        BestReadApiInterface bestReadApiInterface = BestReadApi.buildInstance();
        Call<Library> libraryCall = bestReadApiInterface.modifyLibrary(libraryId, library);
        libraryCall.enqueue(new Callback<Library>() {
            @Override
            public void onResponse(Call<Library> call, Response<Library> response) {
                listener.onModifyLibrarySuccess(response.body());
            }

            @Override
            public void onFailure(Call<Library> call, Throwable t) {
                t.printStackTrace();
                String message = "No se ha podido editar el libro";
                listener.onModifyLibraryError(message);
            }
        });
    }

}
