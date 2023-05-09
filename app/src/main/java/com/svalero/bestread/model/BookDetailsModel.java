package com.svalero.bestread.model;

import android.content.Context;
import android.util.Log;

import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.BookDetailsContract;
import com.svalero.bestread.domain.Book;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDetailsModel implements BookDetailsContract.Model {

    @Override
    public void loadBook(BookDetailsContract.Model.OnDetailBookListener listener, long bookId) {
        BestReadApiInterface bestReadApiInterface = BestReadApi.buildInstance();
        Call<Book> callBook = bestReadApiInterface.getBook(bookId);
        callBook.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Book book = response.body();
                    listener.onDetailBookSuccess(book);
                } else {
                    listener.onDetailBookError("Error al recuperar el libro");
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                listener.onDetailBookError(t.getMessage());
            }
        });
    }
}
