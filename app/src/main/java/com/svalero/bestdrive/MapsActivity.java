package com.svalero.bestdrive;

import static com.svalero.bestdrive.db.Constants.DATABASE_NAME;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.google.android.gms.location.LocationServices;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.MapboxMap;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;
import com.mapbox.maps.plugin.locationcomponent.LocationProvider;
import com.svalero.bestdrive.R;
import com.svalero.bestdrive.db.AppDatabase;
import com.svalero.bestdrive.domain.Notice;


import java.util.List;

public class MapsActivity extends AppCompatActivity {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private FloatingActionButton btUbicacion;
    private LocationServices servicioUbicacion;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        mapView = findViewById(R.id.mapView);
        initializePointManager();

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        List<Notice> notices = db.noticeDao().getAll();
        addNoticesToMap(notices);

    }

    private void addNoticesToMap(List<Notice> notices) {
        for (Notice notice : notices) {
            Point point = Point.fromLngLat(notice.getLongitude(), notice.getLatitude());
            addMarker(point, notice.getName());
        }

        Notice lastNotice = notices.get(notices.size() - 1);
        setCameraPosition(Point.fromLngLat(lastNotice.getLongitude(), lastNotice.getLatitude()));
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
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.mipmap.red_marker));
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