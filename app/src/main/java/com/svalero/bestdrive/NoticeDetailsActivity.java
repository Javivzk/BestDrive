package com.svalero.bestdrive;

import static com.svalero.bestdrive.db.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.svalero.bestdrive.R;
import com.svalero.bestdrive.db.AppDatabase;
import com.svalero.bestdrive.domain.Notice;


public class NoticeDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_details);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        if (name == null)
            return;

        // Cargo los detalles del aviso
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        Notice notice = db.noticeDao().getByName(name);
        fillData(notice);
    }

    private void fillData(Notice notice) {
        TextView tvName = findViewById(R.id.tv_notice_name);
        TextView tvDescription = findViewById(R.id.tv_notice_description);
        TextView tvOwner = findViewById(R.id.tv_notice_publisher);

        tvName.setText(notice.getName());
        tvDescription.setText(notice.getDescription());
        tvOwner.setText(notice.getPublisher());
    }
}