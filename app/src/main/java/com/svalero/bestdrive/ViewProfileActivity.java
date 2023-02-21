package com.svalero.bestdrive;

import static com.svalero.bestdrive.db.Constants.DATABASE_NAME;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.room.Room;

import com.svalero.bestdrive.db.AppDatabase;
import com.svalero.bestdrive.domain.User;

public class ViewProfileActivity extends AppCompatActivity {

    Uri pathImg;
    String transPathImg;
    Button bt_camara;
    ImageView im_profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);

        // IMAGEN DE PERFIL
        bt_camara = findViewById(R.id.bt_camara);
        im_profile = findViewById(R.id.im_profile);

        bt_camara.setOnClickListener(new View.OnClickListener() {
            //Método que verifica versiones y permisos
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    checkExternalStoragePermissions();
                }
                camaraLauncher.launch(new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI));
                if(transPathImg != null) {
                    updateImg(transPathImg);
                }
            }
        });

        //FIN DE IMAGEN DE PERFIL

        TextView tv_profileUserName = findViewById(R.id.tv_profileUserName);
        TextView tv_profileName = findViewById(R.id.tv_profileName);
        TextView tv_profileEmail = findViewById(R.id.tv_profileEmail);
        TextView tv_profileLastName = findViewById(R.id.tv_profileLastName);

        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        User user = db.userDao().getByUsername(LoginActivity.currentUser);

        tv_profileUserName.setText(user.getUserName());
        tv_profileName.setText(user.getName());
        tv_profileEmail.setText(user.getEmail());
        tv_profileLastName.setText(user.getLastName());
    }

    @Override
    protected void onResume() {
        super.onResume();

        TextView tv_profileUserName = findViewById(R.id.tv_profileUserName);
        TextView tv_profileName = findViewById(R.id.tv_profileName);
        TextView tv_profileEmail = findViewById(R.id.tv_profileEmail);
        TextView tv_profileLastName = findViewById(R.id.tv_profileLastName);


        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        User user = db.userDao().getByUsername(LoginActivity.currentUser);

        tv_profileUserName.setText(user.getUserName());
        tv_profileName.setText(user.getName());
        tv_profileEmail.setText(user.getEmail());
        tv_profileLastName.setText(user.getLastName());

    }

    public void modifyUserButton(View view) {
        Intent intent = new Intent(this, ModifyUserActivity.class);
        startActivity(intent);
    }

    public void updateImg(String path) {
        final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                .allowMainThreadQueries().build();
        db.userDao().updateImgUser(LoginActivity.currentUser, path);
    }

    public void deleteUser(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.are_you_sure_message)
                .setTitle(R.string.remove_user_message)
                .setPositiveButton(R.string.yes, (dialog, id) -> {
                    final AppDatabase db = Room.databaseBuilder(this, AppDatabase.class, DATABASE_NAME)
                            .allowMainThreadQueries().build();
                    User user = db.userDao().getByUsername(LoginActivity.currentUser);

                    db.userDao().delete(user);
                    LoginActivity.currentUser = "";

                    Intent intent = new Intent(this, MainActivity.class);
                    startActivity(intent);
                })
                .setNegativeButton(R.string.no, (dialog, id) -> dialog.dismiss());
        AlertDialog dialog = builder.create();
        dialog.show();

    }

    ActivityResultLauncher<Intent> camaraLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == RESULT_OK) {
                        pathImg = result.getData().getData();
                        im_profile.setImageURI(pathImg);
                        transPathImg = getRealPathFromURI(pathImg);
                    }
                }

            });

    //Comprobación de permisos
    private void checkExternalStoragePermissions() {
        int check = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if(check != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 225);
        }
    }

    //Obtenemos el path real
    private String getRealPathFromURI(Uri path) {
        String res;
        Cursor cursor = getContentResolver().query(path, null, null, null, null);
        if(cursor == null) {
            res = path.getPath();
        } else {
            cursor.moveToFirst();
            int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            res = cursor.getString(index);
            cursor.close();
        }

        return res;
    }

}