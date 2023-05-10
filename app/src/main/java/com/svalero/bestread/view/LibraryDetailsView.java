package com.svalero.bestread.view;

import static com.svalero.bestread.api.Constants.DATABASE_NAME;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.svalero.bestread.R;
import com.svalero.bestread.contract.LibraryDetailsContract;
import com.svalero.bestread.contract.ModifyLibraryContract;
import com.svalero.bestread.db.BestReadDatabase;
import com.svalero.bestread.domain.FavoritesLibraries;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.presenter.LibraryDetailsPresenter;
import com.svalero.bestread.presenter.ModifyLibraryPresenter;


public class LibraryDetailsView extends AppCompatActivity implements LibraryDetailsContract.View,ModifyLibraryContract.View {

    private LibraryDetailsPresenter presenter;
    private ModifyLibraryContract.Presenter modifyPresenter;

    long libraryId;

    private Library library;
    private EditText etName;
    private EditText etDescription;
    private EditText etCity;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_details);

        Button btnAddFavorite = findViewById(R.id.btn_add_favorite);
        btnAddFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFavorite();
            }
        });


        Intent intent = getIntent();
        libraryId = intent.getLongExtra("libraryId",0);
        if (libraryId == 0)
            return;

        presenter = new LibraryDetailsPresenter(this);
        etName = findViewById(R.id.et_library_name);
        etDescription = findViewById(R.id.et_library_description);
        etCity = findViewById(R.id.et_library_description);
        modifyPresenter = new ModifyLibraryPresenter(this);
        presenter.loadLibrary(libraryId);

    }

//    public void modifyLibrary(View view) {
//        Intent intent = getIntent();
//
//        String name = intent.getStringExtra("name");
//        if (name == null)
//            return;
//
//        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
//                .allowMainThreadQueries().build();
//
//        Library newLibrary = db.libraryDao().getByName(name);
//
//        EditText descriptionField = findViewById(R.id.et_library_description);
//        EditText publisherField = findViewById(R.id.et_library_city);
//
//        newLibrary.setDescription(descriptionField.getText().toString());
//        newLibrary.setCity(publisherField.getText().toString());
//
//
//        try {
//            db.libraryDao().getByName(newLibrary.getName());
//            db.libraryDao().update(newLibrary);
//            Toast.makeText(this, R.string.library_modified_message, Toast.LENGTH_LONG).show();
//            onBackPressed();
//        } catch (SQLiteConstraintException sce) {
//            Toast.makeText(this, "La libreria no existe", Toast.LENGTH_LONG).show();
//
//        }
//
//    }

    public void modifyLibrary(View view) {

        Intent intent = getIntent();

        libraryId = intent.getLongExtra("libraryId",0);
        if (libraryId == 0) {
            return;
        }

        EditText nameField = findViewById(R.id.et_library_name);
        EditText descriptionField = findViewById(R.id.et_library_description);
        EditText cityField = findViewById(R.id.et_library_city);

        Library updatedLibrary = new Library();

        updatedLibrary.setName(nameField.getText().toString());
        updatedLibrary.setDescription(descriptionField.getText().toString());
        updatedLibrary.setCity(cityField.getText().toString());


        modifyPresenter.modifyLibrary(updatedLibrary, libraryId);

    }
    public void goBackButton(View view) {
        onBackPressed();
    }

    public void setFavorite() {
        FavoritesLibraries favorites = new FavoritesLibraries();
        favorites.setId(this.library.getId());  // Pasamos el id de la librería actual
        favorites.setLibraryId(this.libraryId); // Pasamos el id de la librería actual
        favorites.setLibraryName(this.library.getName()); // Pasamos el nombre de la librería actual
        favorites.setLibraryDescription(this.library.getDescription());
        favorites.setLibraryCity(this.library.getCity());

        final BestReadDatabase db = Room.databaseBuilder(this, BestReadDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        db.getFavoriteDAO().insert(favorites);

        Toast.makeText(this, "Se ha añadido a Favoritos", Toast.LENGTH_SHORT).show();
    }





    @Override
    public void showLibrary(Library library) {
        EditText etName = findViewById(R.id.et_library_name);
        EditText etDescription = findViewById(R.id.et_library_description);
        EditText etOwner = findViewById(R.id.et_library_city);

        etName.setText(library.getName());
        etDescription.setText(library.getDescription());
        etOwner.setText(library.getCity());
        this.library = library;

    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }



}