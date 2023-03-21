package com.svalero.bestread.adapter;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.bestread.view.BookDetailsView;
import com.svalero.bestread.R;
import com.svalero.bestread.db.AppDatabase;
import com.svalero.bestread.domain.Book;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    private Context context;
    private List<Book> bookList;

    public BookAdapter(Context context, List<Book> dataList) {
        this.context = context;
        this.bookList = dataList;
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
        holder.bookDescription.setText(bookList.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class BookHolder extends RecyclerView.ViewHolder {
        public TextView bookTitle;
        public TextView bookAuthor;
        public TextView bookDescription;
        public Button seeDetailsBookButton;
        public Button deleteBookButton;
        public View parentView;

        public BookHolder(View view) {
            super(view);
            parentView = view;

            bookTitle = view.findViewById(R.id.tv_book_title);
            bookAuthor = view.findViewById(R.id.tv_book_author);
            bookDescription = view.findViewById(R.id.tv_book_description);

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
                    final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries().build();
                    Book book = bookList.get(position);
                    db.bookDao().delete(book);

                    bookList.remove(position);
                    notifyItemRemoved(position);
                }))
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
