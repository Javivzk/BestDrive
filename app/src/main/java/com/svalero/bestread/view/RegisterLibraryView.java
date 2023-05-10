package com.svalero.bestread.view;


import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
import com.svalero.bestread.R;
import com.svalero.bestread.contract.RegisterLibraryContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.presenter.RegisterLibraryPresenter;

public class RegisterLibraryView extends AppCompatActivity implements RegisterLibraryContract.View {

    private MapView libraryMap;
    private Point point;
    private PointAnnotationManager pointAnnotationManager;

    private RegisterLibraryPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_library);

        presenter = new RegisterLibraryPresenter(this);
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

    public void saveButton(View view) {
        EditText etName = findViewById(R.id.edit_text_name);
        EditText etDescription = findViewById(R.id.edit_text_description);
        EditText etCity = findViewById(R.id.edit_text_city);

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();
        String city = etCity.getText().toString();

        if (point == null) {
            Toast.makeText(this, R.string.select_location_message, Toast.LENGTH_LONG).show();
            return;
        }

        Library library = new Library(name, description, city,false, point.latitude(), point.longitude());
        presenter.registerLibrary(library);
    }

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
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.red_marker))
                .withIconSize(0.5f);
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    private void removeAllMarkers() {
        pointAnnotationManager.deleteAll();
    }

    @Override
    public void showError(String errorMessage) {
        Snackbar.make(((EditText) findViewById(R.id.edit_text_name)), errorMessage,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(((EditText) findViewById(R.id.edit_text_name)), message,
                BaseTransientBottomBar.LENGTH_LONG).show();
    }

    @Override
    public void resetForm() {
        ((EditText) findViewById(R.id.edit_text_name)).setText("");
        ((EditText) findViewById(R.id.edit_text_description)).setText("");
        ((EditText) findViewById(R.id.edit_text_city)).setText("");
        ((EditText) findViewById(R.id.edit_text_name)).requestFocus();
    }
}
