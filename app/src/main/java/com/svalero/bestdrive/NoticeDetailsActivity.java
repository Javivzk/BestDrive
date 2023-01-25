package com.svalero.bestdrive;

import static com.svalero.bestdrive.db.Constants.DATABASE_NAME;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
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
        EditText etName = findViewById(R.id.et_notice_name);
        EditText etDescription = findViewById(R.id.et_notice_description);
        EditText etOwner = findViewById(R.id.et_notice_publisher);

        etName.setText(notice.getName());
        etDescription.setText(notice.getDescription());
        etOwner.setText(notice.getPublisher());
    }

    public void modifyNotice(View view) {
        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        if (name == null)
            return;

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();

        Notice newNotice = db.noticeDao().getByName(name);

        EditText descriptionField = findViewById(R.id.et_notice_description);
        EditText publisherField = findViewById(R.id.et_notice_publisher);

        newNotice.setDescription(descriptionField.getText().toString());
        newNotice.setPublisher(publisherField.getText().toString());


        try {
            db.noticeDao().getByName(newNotice.getName());
            db.noticeDao().update(newNotice);
            Toast.makeText(this, R.string.notice_modified_message, Toast.LENGTH_LONG).show();
            onBackPressed();
        } catch (SQLiteConstraintException sce) {
            Toast.makeText(this, "El aviso no existe", Toast.LENGTH_LONG).show();

        }
    }
}