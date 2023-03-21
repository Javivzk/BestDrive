package com.svalero.bestread.adapter;

import static com.svalero.bestread.db.Constants.DATABASE_NAME;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import com.svalero.bestread.R;
import com.svalero.bestread.view.LibraryDetailsView;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.db.AppDatabase;

import java.util.List;


public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.LibraryHolder> {

    private Context context;
    private List<Library> libraryList;

    public LibraryAdapter(Context context, List<Library> dataList) {
        this.context = context;
        this.libraryList = dataList;
    }

    @Override
    public LibraryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_library_item, parent, false);
        return new LibraryHolder(view);
    }

    @Override
    public void onBindViewHolder(LibraryHolder holder, int position) {
        holder.libraryName.setText(libraryList.get(position).getName());
        holder.libraryDescription.setText(libraryList.get(position).getDescription());
        holder.libraryVerified.setChecked(libraryList.get(position).isVerify());
    }

    @Override
    public int getItemCount() {
        return libraryList.size();
    }

    public class LibraryHolder extends RecyclerView.ViewHolder {
        public TextView libraryName;
        public TextView libraryDescription;
        public CheckBox libraryVerified;
        public Button doLibraryButton;
        public Button seeDetailsButton;
        public Button deleteLibraryButton;
        public View parentView;

        public LibraryHolder(View view) {
            super(view);
            parentView = view;

            libraryName = view.findViewById(R.id.tv_library_name);
            libraryDescription = view.findViewById(R.id.tv_library_description);
            libraryVerified = view.findViewById(R.id.check_library_verified);
            doLibraryButton = view.findViewById(R.id.do_library_button);
            seeDetailsButton = view.findViewById(R.id.see_details_button);
            deleteLibraryButton = view.findViewById(R.id.delete_library_button);

            // Marcar tarea como hecha
            doLibraryButton.setOnClickListener(v -> doLibrary(getAdapterPosition()));
            // Ver detalles
            seeDetailsButton.setOnClickListener(v -> seeDetails(getAdapterPosition()));
            // Eliminar tarea
            deleteLibraryButton.setOnClickListener(v -> deleteLibrary(getAdapterPosition()));

        }
    }

    private void doLibrary(int position) {
        Library library = libraryList.get(position);
        library.setVerify(true);

        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.libraryDao().update(library);

        notifyItemChanged(position);
    }

    private void seeDetails(int position) {
        Library library = libraryList.get(position);

        Intent intent = new Intent(context, LibraryDetailsView.class);
        intent.putExtra("name", library.getName());
        context.startActivity(intent);
    }

    private void deleteLibrary(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.are_you_sure_message)
                .setTitle(R.string.remove_book_message)
                .setPositiveButton(R.string.yes, ((dialog, id) -> {
                    final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries().build();
                    Library library = libraryList.get(position);
                    db.libraryDao().delete(library);

                    libraryList.remove(position);
                    notifyItemRemoved(position);
                }))
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
