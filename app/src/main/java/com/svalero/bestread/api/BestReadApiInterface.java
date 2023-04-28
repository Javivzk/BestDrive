package com.svalero.bestread.api;

import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.JwtRequest;
import com.svalero.bestread.domain.Library;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface BestReadApiInterface {

    @GET("libraries")
    Call<List<Library>> getLibraries();

    @GET("library/{libraryId}")
    Call<Library> getLibrary(@Path("libraryId") long libraryId);

    @POST("libraries")
    Call<Library> addLibrary(@Body Library library);

    @DELETE("library/{libraryId}")
    Call<Void> deleteLibrary(@Path("libraryId") long libraryId);

    @GET("books")
    Call<List<Book>> getBooks();

    @GET("book/{bookId}")
    Call<Book> getBook(@Path("bookId") long bookId);

    @POST("books")
    Call<Book> addBook(@Body Book book);

    @DELETE("book/{bookId}")
    Call<Void> deleteBook(@Path("bookId") long bookId);

    @POST("login")
    Call<Void> login(@Body JwtRequest jwtRequest);

}
