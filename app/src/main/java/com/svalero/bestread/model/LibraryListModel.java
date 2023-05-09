package com.svalero.bestread.model;

import android.util.Log;

import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.LibraryListContract;
import com.svalero.bestread.domain.Library;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibraryListModel implements LibraryListContract.Model {

    @Override
    public void loadAllLibraries(OnLoadLibrariesListener listener) {
        BestReadApiInterface bestReadApi = BestReadApi.buildInstance();
        Call<List<Library>> callLibraries = bestReadApi.getLibraries();
        Log.d("libraries", "LLamada desde model");

        callLibraries.enqueue(new Callback<List<Library>>() {
            @Override
            public void onResponse(Call<List<Library>> call, Response<List<Library>> response) {
                Log.d("libraries", "LLamada desde model ok");

                List<Library> libraries = response.body();
                listener.onLoadLibrariesSuccess(libraries);

            }

            @Override
            public void onFailure(Call<List<Library>> call, Throwable t) {
                Log.d("libraries", "LLamada desde model error");
                t.printStackTrace();

                String message = "Error invocando a la operacion";
                listener.onLoadLibrariesError(message);

            }
        });


//        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
//                .allowMainThreadQueries().build();
//        return db.libraryDao().getAll();
    }

    @Override
    public Library loadLibrariesByName(String name) {
        return null;
    }

    @Override
    public List<Library> getAll() {
        return null;
    }

    @Override
    public Library getById(long id) {
        return null;
    }


    @Override
    public void insert(Library library) {

    }

    @Override
    public boolean deleteLibrary(String name) {
        return false;
    }

    @Override
    public void update(Library library) {

    }
}
