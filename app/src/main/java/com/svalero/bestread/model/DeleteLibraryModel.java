package com.svalero.bestread.model;

import android.database.sqlite.SQLiteConstraintException;

import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.DeleteLibraryContract;
import com.svalero.bestread.domain.Library;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteLibraryModel implements DeleteLibraryContract.Model {

    @Override
    public void deleteLibrary(long libraryId, OnDeleteLibraryListener listener) {
        try {
            BestReadApiInterface bestReadApi = BestReadApi.buildInstance();
            Call<Void> callLibraries = bestReadApi.deleteLibrary(libraryId);
            callLibraries.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    listener.onDeleteSuccess();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error invocando a la operacion";
                    listener.onDeleteError(message);
                }
            });
        }catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}