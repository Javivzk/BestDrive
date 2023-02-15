package com.svalero.bestdrive;

import static com.svalero.bestdrive.db.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.svalero.bestdrive.db.AppDatabase;
import com.svalero.bestdrive.domain.Library;


public class LibraryDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name == null)
            return;

        // Cargo los detalles de la libreria
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Library library = db.libraryDao().getByName(name);
        fillData(library);

    }


    private void fillData(Library library) {
        EditText etName = findViewById(R.id.et_library_name);
        EditText etDescription = findViewById(R.id.et_library_description);
        EditText etOwner = findViewById(R.id.et_library_publisher);

        etName.setText(library.getName());
        etDescription.setText(library.getDescription());
        etOwner.setText(library.getPublisher());
    }

    public void modifyLibrary(View view) {
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        if (name == null)
            return;

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        Library newLibrary = db.libraryDao().getByName(name);

        EditText descriptionField = findViewById(R.id.et_library_description);
        EditText publisherField = findViewById(R.id.et_library_publisher);

        newLibrary.setDescription(descriptionField.getText().toString());
        newLibrary.setPublisher(publisherField.getText().toString());


        try {
            db.libraryDao().getByName(newLibrary.getName());
            db.libraryDao().update(newLibrary);
            Toast.makeText(this, R.string.library_modified_message, Toast.LENGTH_LONG).show();
            onBackPressed();
        } catch (SQLiteConstraintException sce) {
            Toast.makeText(this, "La libreria no existe", Toast.LENGTH_LONG).show();

        }
    }
}