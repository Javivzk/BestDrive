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

    private Context context;

    public BookDetailsModel(Context context) {
        this.context = context;
    }


    @Override
    public void loadBook(OnDetailBookListener listener, long bookId) {
        BestReadApiInterface bestReadApiInterface = BestReadApi.buildInstance();
        Call<Book> callBook = bestReadApiInterface.getBook(bookId);
        callBook.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                Log.d("book", "LLamada desde model ok");

                Book book = response.body();
                listener.onDetailBookSuccess(book);

            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                t.printStackTrace();
                String message = "Error invocando a la operación";
                listener.onDetailBookError(message);
            }
        });
    }

}
