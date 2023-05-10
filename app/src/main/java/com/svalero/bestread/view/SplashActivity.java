package com.svalero.bestread.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.svalero.bestread.R;
import com.svalero.bestread.adapter.LibraryAdapter;
import com.svalero.bestread.domain.Library;

import java.util.List;

public class SplashActivity extends AppCompatActivity {

    private List<Library> noticeList;
    private LibraryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //Agregar animacion
        Animation animacion1 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_arriba);
        Animation animacion2 = AnimationUtils.loadAnimation(this, R.anim.desplazamiento_abajo);

        TextView titleTextView = findViewById(R.id.tv_splash_title);
        TextView descriptionTextView = findViewById(R.id.tv_splash_description);
        ImageView logoImageView = findViewById(R.id.iv_splash_logo);
        ProgressBar progressBar = findViewById(R.id.progressBar);

        titleTextView.setAnimation(animacion2);
        descriptionTextView.setAnimation(animacion2);
        logoImageView.setAnimation(animacion1);
        progressBar.setAnimation(animacion1);


        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginView.class);
                startActivity(intent);
                finish();
            }
        }, 4000);

    }
}