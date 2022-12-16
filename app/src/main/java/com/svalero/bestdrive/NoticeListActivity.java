package com.svalero.bestdrive;
import static com.svalero.bestdrive.db.Constants.DATABASE_NAME;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.svalero.bestdrive.adapter.NoticeAdapter;
import com.svalero.bestdrive.db.AppDatabase;
import com.svalero.bestdrive.domain.Notice;

import java.util.ArrayList;
import java.util.List;

public class NoticeListActivity extends AppCompatActivity  {

    private List<Notice> noticeList;
    private NoticeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_list);

        noticeList = new ArrayList<>();

        RecyclerView recyclerView = findViewById(R.id.notice_list);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new NoticeAdapter(this,noticeList);
        recyclerView.setAdapter(adapter);

    }

    @Override
    protected void onResume() {
        super.onResume();

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        noticeList.clear();
        noticeList.addAll(db.noticeDao().getAll());
        adapter.notifyDataSetChanged();
    }
}