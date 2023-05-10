package com.svalero.bestread.view;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.bestread.R;
import com.svalero.bestread.db.BestReadDatabase;
import com.svalero.bestread.db.dao.FavoritesDAO;
import com.svalero.bestread.domain.FavoritesLibraries;

import java.util.List;

public class FavoritesView extends AppCompatActivity {

    private FavoritesDAO favoritesDAO;
    private List<FavoritesLibraries> favoritesList;

    private RecyclerView recyclerView;
    private FavoritesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites_view);

        BestReadDatabase db = Room.databaseBuilder(getApplicationContext(),
                BestReadDatabase.class, "library").build();
        favoritesDAO = db.getFavoriteDAO();

        recyclerView = findViewById(R.id.rv_favorites);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new FavoritesAdapter();
        recyclerView.setAdapter(adapter);

        loadFavorites();
    }
    public void goBackButton(View view) {
        onBackPressed();
    }

    private void loadFavorites() {
        new AsyncTask<Void, Void, List<FavoritesLibraries>>() {
            @Override
            protected List<FavoritesLibraries> doInBackground(Void... voids) {
                return favoritesDAO.getAll();
            }

            @Override
            protected void onPostExecute(List<FavoritesLibraries> favoritesLibraries) {
                favoritesList = favoritesLibraries;
                adapter.notifyDataSetChanged();
            }
        }.execute();
    }

    private class FavoritesViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDescription;
        TextView tvCity;

        FavoritesViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_library_name);
            tvDescription = itemView.findViewById(R.id.tv_library_description);
            tvCity = itemView.findViewById(R.id.tv_library_city);

        }

        void bind(FavoritesLibraries favorites) {
            tvName.setText(favorites.getLibraryName());
            tvDescription.setText(favorites.getLibraryDescription());
            tvCity.setText(favorites.getLibraryCity());

        }
    }

    private class FavoritesAdapter extends RecyclerView.Adapter<FavoritesViewHolder> {
        @NonNull
        @Override
        public FavoritesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.favorite_item, parent, false);
            return new FavoritesViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull FavoritesViewHolder holder, int position) {
            holder.bind(favoritesList.get(position));
        }

        @Override
        public int getItemCount() {
            return favoritesList != null ? favoritesList.size() : 0;
        }
    }
}
