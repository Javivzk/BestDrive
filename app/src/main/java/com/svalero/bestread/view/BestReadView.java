package com.svalero.bestread.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.svalero.bestread.R;
import com.svalero.bestread.contract.BestReadContract;
import com.svalero.bestread.domain.Book;
import com.svalero.bestread.presenter.BestReadPresenter;

import java.util.List;

public class BestReadView extends AppCompatActivity implements View.OnClickListener, BestReadContract.View {

    private BestReadPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new BestReadPresenter(this);

        initializeMainScreen();

    }

    private void initializeMainScreen() {
        Button viewMapButton = findViewById(R.id.viewMapButton);
        viewMapButton.setOnClickListener(this);
        Button viewLibraryButton = findViewById(R.id.viewLibraryButton);
        viewLibraryButton.setOnClickListener(this);
        Button viewBookButton = findViewById(R.id.viewBookButton);
        viewBookButton.setOnClickListener(this);
        Button openCameraButton = findViewById(R.id.openCameraButton);
        openCameraButton.setOnClickListener(this);


        Toast.makeText(this, R.string.remember_message,Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.viewMapButton) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);

        } else if (view.getId() == R.id.viewLibraryButton) {
            Intent intent = new Intent(this, LibraryListActivity.class);
            startActivity(intent);
        } else if (view.getId() == R.id.viewBookButton) {
            Intent intent = new Intent(this, BookListView.class);
            startActivity(intent);
        }else if (view.getId() == R.id.openCameraButton){
            Intent intent = new Intent(this, CameraActivity.class);
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
        }else if (item.getItemId() == R.id.register_book) {
            Intent intent = new Intent(this, RegisterBookActivity.class);
            startActivity(intent);
        }
        return false;
    }

    public void continueButton(View view) {

    }


    @Override
    public void showBooks(List<Book> books) {

    }

    @Override
    public void showMessage(String message) {

    }
}
