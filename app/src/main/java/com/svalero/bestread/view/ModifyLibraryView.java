package com.svalero.bestread.view;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

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
import com.svalero.bestread.R;

public class ModifyLibraryView extends AppCompatActivity {

    private MapView libraryMap;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_library);

        libraryMap = findViewById(R.id.libraryMap);

        GesturesPlugin gesturesPlugin = GesturesUtils.getGestures(libraryMap);
        gesturesPlugin.addOnMapClickListener(point -> {
            removeAllMarkers();
            this.point = point;
            addMarker(point);
            return true;
        });

        initializePointManager();
    }

//    public void saveButton(View view) {
//        EditText nameField = (EditText) findViewById(R.id.edit_text_name);
//        EditText descriptionField = (EditText) findViewById(R.id.edit_text_description);
//        EditText cityField = (EditText) findViewById(R.id.edit_text_city);
//
//        String name = nameField.getText().toString();
//        String description = descriptionField.getText().toString();
//        String city = cityField.getText().toString();
//
//
//        if (point == null) {
//            Toast.makeText(this, R.string.select_location_message, Toast.LENGTH_LONG).show();
//            return;
//        }
//
//        Library library = new Library(name, description, city,false, point.latitude(), point.longitude());
//        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
//                .allowMainThreadQueries().build();
//        try {
//            db.libraryDao().update(library);
//
//
//            Toast.makeText(this, R.string.task_modified_message, Toast.LENGTH_LONG).show();
//            nameField.setText("");
//            descriptionField.setText("");
//            cityField.setText("");
//            nameField.requestFocus();
//        } catch (SQLiteConstraintException sce) {
//            Snackbar.make(nameField, R.string.task_registered_error, BaseTransientBottomBar.LENGTH_LONG).show();
//            //Toast.makeText(this, R.string.task_registered_error, Toast.LENGTH_LONG).show();
//        }
//    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(libraryMap);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    public void goBackButton(View view) {
        onBackPressed();
    }

    private void addMarker(Point point) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.red_marker));
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    private void removeAllMarkers() {
        pointAnnotationManager.deleteAll();
    }

}


