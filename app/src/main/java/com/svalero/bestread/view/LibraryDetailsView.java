package com.svalero.bestread.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.bestread.R;
import com.svalero.bestread.contract.LibraryDetailsContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.presenter.LibraryDetailsPresenter;


public class LibraryDetailsView extends AppCompatActivity implements LibraryDetailsContract.View {

    private LibraryDetailsPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_details);

        presenter = new LibraryDetailsPresenter(this);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name == null)
            return;

        SharedPreferences preferences = getSharedPreferences(getString(R.string.app_name), Context.MODE_PRIVATE);
        String username = preferences.getString("username" ,"");
        String password = preferences.getString("password", "");

        presenter.loadLibrary(name);
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

    @Override
    public void showLibrary(Library library) {
        EditText etName = findViewById(R.id.et_library_name);
        EditText etDescription = findViewById(R.id.et_library_description);
        EditText etOwner = findViewById(R.id.et_library_city);

        etName.setText(library.getName());
        etDescription.setText(library.getDescription());
        etOwner.setText(library.getCity());
    }
}