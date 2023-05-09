package com.svalero.bestread.view;


import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.svalero.bestread.R;
import com.svalero.bestread.contract.LibraryListContract;
import com.svalero.bestread.domain.Library;
import com.svalero.bestread.presenter.LibraryMapPresenter;


import java.util.List;

public class LibraryMapView extends AppCompatActivity implements LibraryListContract.View {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private LibraryMapPresenter librariesMapPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapView = findViewById(R.id.mapView);
        librariesMapPresenter = new LibraryMapPresenter(this);
        librariesMapPresenter.loadAllLibraries();
        initializePointManager();

//        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
//                .allowMainThreadQueries().build();
//        List<Library> notices = db.libraryDao().getAll();
//        addNoticesToMap(notices);

    }

    @Override
    public void showLibraries(List<Library> librariesList) {
        for (Library library : librariesList) {
            Point point = Point.fromLngLat(library.getLongitude(), library.getLatitude());
            addMarker(point, library.getName());
        }

        Library lastLibrary = librariesList.get(librariesList.size() - 1);
        setCameraPosition(Point.fromLngLat(lastLibrary.getLongitude(), lastLibrary.getLatitude()));
    }

    @Override
    public void showMessage(String message) {

    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    private void addMarker(Point point, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withTextField(title)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.red_marker));
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(0.0)
                .zoom(13.5)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }

}