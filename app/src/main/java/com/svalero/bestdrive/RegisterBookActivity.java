package com.svalero.bestdrive;

import static com.svalero.bestdrive.db.Constants.DATABASE_NAME;

import android.database.sqlite.SQLiteConstraintException;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.mapbox.geojson.Point;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.gestures.GesturesPlugin;
import com.mapbox.maps.plugin.gestures.GesturesUtils;
import com.svalero.bestdrive.db.AppDatabase;
import com.svalero.bestdrive.domain.Book;
import com.svalero.bestdrive.domain.Library;

public class RegisterBookActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_book);
    }

    public void saveButton(View view) {
        EditText etTitle = findViewById(R.id.edit_text_title);
        EditText etAuthor = findViewById(R.id.edit_text_author);
        EditText etDescription = findViewById(R.id.edit_text_description);


        String title = etTitle.getText().toString();
        String author = etAuthor.getText().toString();
        String description = etDescription.getText().toString();



        Book book = new Book(title, author, description);
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        try {
            db.bookDao().insert(book);

            Toast.makeText(this, R.string.task_book_registered_message, Toast.LENGTH_LONG).show();
            etTitle.setText("");
            etAuthor.setText("");
            etDescription.setText("");
            etTitle.requestFocus();
        } catch (SQLiteConstraintException sce) {
            Snackbar.make(etTitle, R.string.task_registered_error, BaseTransientBottomBar.LENGTH_LONG).show();
            //Toast.makeText(this, R.string.task_registered_error, Toast.LENGTH_LONG).show();
        }
    }


    public void goBackButton(View view) {
        onBackPressed();
    }


}
