package com.example.listview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class CameraActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgCaptureResult;
    private Button btnOpnCam, btnSvimg;
    private final int CAMERA_REQUEST_CODE = 400;
    private final int WRITE_STORAGE_REQUEST_CODE = 401;
    private final int REQUEST_SAVE_IMAGE = 200;
    private OutputStream outputStream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        imgCaptureResult = findViewById(R.id.img_capture_result);
        btnOpnCam = findViewById(R.id.btn_Cam);
        btnSvimg = findViewById(R.id.btn_svImg);

        btnOpnCam.setOnClickListener(this);
        btnSvimg.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_Cam:
                Intent intenCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intenCamera, CAMERA_REQUEST_CODE);
                return;
            case R.id.btn_svImg:
                saveImage();
                return;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgCaptureResult.setImageBitmap(imageBitmap);
            btnSvimg.setVisibility(View.VISIBLE);

        } else if (requestCode == REQUEST_SAVE_IMAGE && resultCode == RESULT_OK) {
            // Simpan gambar ke lokasi yang dipilih
            Uri uri = data.getData();
            try {
                OutputStream outputStream = getContentResolver().openOutputStream(uri);
                BitmapDrawable drawable = (BitmapDrawable) imgCaptureResult.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
                outputStream.flush();
                outputStream.close();
                Toast.makeText(this, "Gambar berhasil disimpan", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Gagal menyimpan gambar", Toast.LENGTH_SHORT).show();
            }
            btnSvimg.setVisibility(View.GONE);
        }
    }

    private void saveImage(){
        if (imgCaptureResult.getDrawable() == null) {
            Toast.makeText(this, "Belum ada gambar yang diambil", Toast.LENGTH_SHORT).show();
            return;
        }
        // cek apakah sudah memiliki izin akses penyimpanan
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_SAVE_IMAGE);
        } else {
            // dapatkan gambar dari ImageView
            BitmapDrawable drawable = (BitmapDrawable) imgCaptureResult.getDrawable();
            Bitmap bitmap = drawable.getBitmap();
            // tampilkan dialog untuk memilih lokasi penyimpanan gambar
            Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/jpeg");
            String ImageName = "prtmn1" + System.currentTimeMillis() + ".JPG";
            intent.putExtra(Intent.EXTRA_TITLE, ImageName);
            startActivityForResult(intent, REQUEST_SAVE_IMAGE);
        }
    }
}