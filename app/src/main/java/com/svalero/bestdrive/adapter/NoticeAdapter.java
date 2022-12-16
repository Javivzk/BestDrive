package com.svalero.bestdrive.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;


import com.svalero.bestdrive.R;
import com.svalero.bestdrive.NoticeDetailsActivity;
import com.svalero.bestdrive.domain.Notice;
import com.svalero.bestdrive.db.AppDatabase;

import java.util.List;


public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeHolder> {

    private Context context;
    private List<Notice> noticeList;

    public NoticeAdapter(Context context, List<Notice> dataList) {
        this.context = context;
        this.noticeList = dataList;
    }

    @Override
    public NoticeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_notice_item, parent, false);
        return new NoticeHolder(view);
    }

    @Override
    public void onBindViewHolder(NoticeHolder holder, int position) {
        holder.noticeName.setText(noticeList.get(position).getName());
        holder.noticeDescription.setText(noticeList.get(position).getDescription());
        holder.noticeVerified.setChecked(noticeList.get(position).isVerify());
    }

    @Override
    public int getItemCount() {
        return noticeList.size();
    }

    public class NoticeHolder extends RecyclerView.ViewHolder {
        public TextView noticeName;
        public TextView noticeDescription;
        public CheckBox noticeVerified;
        public Button doNoticeButton;
        public Button seeDetailsButton;
        public Button deleteNoticeButton;
        public View parentView;

        public NoticeHolder(View view) {
            super(view);
            parentView = view;

            noticeName = view.findViewById(R.id.tv_notice_name);
            noticeDescription = view.findViewById(R.id.tv_notice_description);
            noticeVerified = view.findViewById(R.id.check_notice_verified);
            doNoticeButton = view.findViewById(R.id.do_notice_button);
            seeDetailsButton = view.findViewById(R.id.see_details_button);
            deleteNoticeButton = view.findViewById(R.id.delete_notice_button);

            // Marcar tarea como hecha
            doNoticeButton.setOnClickListener(v -> doNotice(getAdapterPosition()));
            // Ver detalles
            seeDetailsButton.setOnClickListener(v -> seeDetails(getAdapterPosition()));
            // Eliminar tarea
            deleteNoticeButton.setOnClickListener(v -> deleteNotice(getAdapterPosition()));

        }
    }

    private void doNotice(int position) {
        Notice notice = noticeList.get(position);
        notice.setVerify(true);

        final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "notices")
                .allowMainThreadQueries().build();
        db.noticeDao().update(notice);

        notifyItemChanged(position);
    }

    private void seeDetails(int position) {
        Notice notice = noticeList.get(position);

        Intent intent = new Intent(context, NoticeDetailsActivity.class);
        intent.putExtra("name", notice.getName());
        context.startActivity(intent);
    }

    private void deleteNotice(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage(R.string.are_you_sure_message)
                .setTitle(R.string.remove_notice_message)
                .setPositiveButton(R.string.yes, ((dialog, id) -> {
                    final AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "notices")
                            .allowMainThreadQueries().build();
                    Notice notice = noticeList.get(position);
                    db.noticeDao().delete(notice);

                    noticeList.remove(position);
                    notifyItemRemoved(position);
                }))
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
