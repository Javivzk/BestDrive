package com.svalero.bestread.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.svalero.bestread.R;
import com.svalero.bestread.contract.DeleteBookContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.presenter.DeleteBookPresenter;
import com.svalero.bestread.view.BookDetailsView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder>
        implements DeleteBookContract.View{

    private Context context;
    private List<Book> bookList;

    private DeleteBookPresenter presenter;

    private View snackBarView;

    public Context getContext() {
        return context;
    }


    public BookAdapter(Context context, List<Book> dataList) {
        this.context = context;
        this.bookList = dataList;
        presenter = new DeleteBookPresenter(this);

    }

    @Override
    public BookHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_book_item, parent, false);
        return new BookHolder(view);
    }

    @Override
    public void onBindViewHolder(BookHolder holder, int position) {
        holder.bookTitle.setText(bookList.get(position).getTitle());
        holder.bookAuthor.setText(bookList.get(position).getAuthor());
        holder.bookGenre.setText(bookList.get(position).getGenre());



    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(snackBarView, errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(snackBarView, message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    public class BookHolder extends RecyclerView.ViewHolder {
        public TextView bookTitle;
        public TextView bookCode;
        public TextView bookYear;
        public TextView bookGenre;
        public TextView bookPages;
        public TextView bookPrice;
        public TextView bookHasStock;

        public TextView bookAuthor;
        public TextView bookDescription;
        public Button seeDetailsBookButton;
        public Button deleteBookButton;
        public View parentView;

        public BookHolder(View view) {
            super(view);
            parentView = view;
            snackBarView = parentView;


            bookTitle = view.findViewById(R.id.tv_book_title);
            bookAuthor = view.findViewById(R.id.tv_book_author);
            bookGenre = view.findViewById(R.id.tv_book_genre);
            seeDetailsBookButton = view.findViewById(R.id.see_details_book_button);
            deleteBookButton = view.findViewById(R.id.delete_book_button);


            // Ver detalles
            seeDetailsBookButton.setOnClickListener(v -> seeDetails(getAdapterPosition()));
            // Eliminar tarea
            deleteBookButton.setOnClickListener(v -> deleteBook(getAdapterPosition()));

        }
    }

    private void seeDetails(int position) {
        Book book = bookList.get(position);

        Intent intent = new Intent(context, BookDetailsView.class);
        intent.putExtra("title", book.getTitle());
        context.startActivity(intent);
    }

    private void deleteBook(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.are_you_sure_message)
                .setTitle(R.string.remove_book_message)
                .setPositiveButton(R.string.yes, ((dialog, id) -> {
                    Book book = bookList.get(position);
                    presenter.deleteBook(book.getId());

                    bookList.remove(position);
                    notifyItemRemoved(position);
                }))
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }

}
