package com.svalero.bestread.api;

import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.JwtRequest;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.domain.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @PUT("library/{libraryId}")
    Call<Library> modifyLibrary(@Path("libraryId") long libraryId ,@Body Library updatedLibrary);
    @GET("books")
    Call<List<Book>> getBooks();

    @GET("book/{bookId}")
    Call<Book> getBook(@Path("bookId") long bookId);

    @POST("books")
    Call<Book> addBook(@Body Book book);

    @PUT("book/{bookId}")
    Call<Book> modifyBook(@Path("bookId") long bookId ,@Body Book updatedBook);

    @DELETE("book/{bookId}")
    Call<Void> deleteBook(@Path("bookId") long bookId);

    @POST("login")
    Call<Void> login(@Body JwtRequest jwtRequest);

    @POST("users")
    Call<User> addUser(@Body User user);
    @DELETE("users/{user_id}")
    Call<Void> deleteUser(@Path("user_id") long id);

    @DELETE("favourites/{favourite_id}")
    Call<Void> deleteFavourite(@Path("favourite_id") long id);

    @GET("users/{userId}")
    Call<User> getUserById(@Path("userId") long userId);

    @GET("user")
    Call<User> getUserLogin(@Query("username") String username, @Query("password") String password);

}
