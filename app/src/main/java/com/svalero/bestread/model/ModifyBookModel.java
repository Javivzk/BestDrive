package com.svalero.bestread.model;

import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.ModifyBookContract;
import com.svalero.bestread.domain.Book;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModifyBookModel implements ModifyBookContract.Model {
    @Override
    public void modifyBook(Book book, OnModifyBookListener listener, long bookId) {
        BestReadApiInterface bestReadApiInterface = BestReadApi.buildInstance();
        Call<Book> bookCall = bestReadApiInterface.modifyBook(bookId,  book);
        bookCall.enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                listener.onModifyBookSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                t.printStackTrace();
                String message = "No se ha podido editar el libro";
                listener.onModifyBookError(message);
            }
        });
    }

}
