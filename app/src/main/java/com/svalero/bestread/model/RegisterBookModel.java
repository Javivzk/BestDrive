package com.svalero.bestread.model;

import android.database.sqlite.SQLiteConstraintException;

import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.RegisterBookContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterBookModel implements RegisterBookContract.Model {


    @Override
    public void registerBook(Book book, RegisterBookContract.Model.OnRegisterBookListener listener) {
        try {
            BestReadApiInterface bestReadApi = BestReadApi.buildInstance();
            Call<Book> callBooks = bestReadApi.addBook(book);
            callBooks.enqueue(new Callback<Book>() {
                @Override
                public void onResponse(Call<Book> call, Response<Book> response) {
                    Book book = response.body();
                    listener.onRegisterBookSuccess(book);

                }

                @Override
                public void onFailure(Call<Book> call, Throwable t) {
                    t.printStackTrace();
                    String message = "Error invocando a la operacion";
                    listener.onRegisterBookError(message);

                }
            });
        }catch (SQLiteConstraintException sce) {
            sce.printStackTrace();
        }
    }
}