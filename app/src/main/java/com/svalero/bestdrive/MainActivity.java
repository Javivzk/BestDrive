package com.svalero.bestdrive;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(this);
        Button navigateButton = findViewById(R.id.navigateButton);
        navigateButton.setOnClickListener(this);
        Button viewMapButton = findViewById(R.id.viewMapButton);
        viewMapButton.setOnClickListener(this);
        Button viewNoticeButton = findViewById(R.id.viewLibraryButton);
        viewNoticeButton.setOnClickListener(this);
        Button openCameraButton = findViewById(R.id.openCameraButton);
        openCameraButton.setOnClickListener(this);


        Toast.makeText(this, R.string.remember_message,Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.navigateButton) {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.viewMapButton) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.viewLibraryButton) {
            Intent intent = new Intent(this, LibraryListActivity.class);
            startActivity(intent);
        }else if (view.getId() == R.id.openCameraButton){
            Intent intent = new Intent(this, CameraActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.registerButton){
            Intent intent = new Intent(this, RegisterLibraryActivity.class);
            startActivity(intent);
    }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.register_library) {
            Intent intent = new Intent(this, RegisterLibraryActivity.class);
            startActivity(intent);
            return true;
        }else if (item.getItemId() == R.id.view_map) {
            Intent intent = new Intent(this, MapsActivity.class);
            startActivity(intent);
        }else if (item.getItemId() == R.id.view_settings) {
            Intent intent = new Intent(this, PreferencesActivity.class);
            startActivity(intent);
        }else if (item.getItemId() == R.id.view_profile) {
            Intent intent = new Intent(this, ViewProfileActivity.class);
            startActivity(intent);
        }
        return false;
    }

    public void continueButton(View view) {

    }


}
