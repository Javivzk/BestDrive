package com.svalero.bestread.model;

import android.content.Context;
import android.util.Log;

import com.svalero.bestread.api.BestReadApi;
import com.svalero.bestread.api.BestReadApiInterface;
import com.svalero.bestread.contract.BookListContract;
import com.svalero.bestread.contract.LibraryListContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListModel implements BookListContract.Model {

    @Override
    public void loadAllBooks(BookListContract.Model.OnLoadBooksListener listener) {
        BestReadApiInterface bestReadApi = BestReadApi.buildInstance();
        Call<List<Book>> callBooks = bestReadApi.getBooks();
        Log.d("books", "LLamada desde model");

        callBooks.enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                Log.d("books", "LLamada desde model ok");

                List<Book> books = response.body();
                listener.onLoadBooksSuccess(books);

            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                Log.d("books", "LLamada desde model error");
                t.printStackTrace();

                String message = "Error invocando a la operacion";
                listener.onLoadBooksError(message);

            }
        });
    }

    @Override
    public Book loadBookByTitle(String title) {
        return null;
    }

    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Book getById(long id) {
        return null;
    }


    @Override
    public void insert(Book book) {

    }

    @Override
    public boolean deleteBook(String title) {
        return false;
    }


    @Override
    public void update(Book book) {

    }
}