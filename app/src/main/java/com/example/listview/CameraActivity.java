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
    private final int CAMERA_REQUEST_CDE = 400;
    private final int WRITE_STORAGE_REQUEST_CODE = 401;
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
                startActivityForResult(intenCamera, CAMERA_REQUEST_CDE);
                return;
            case R.id.btn_svImg:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                       == PackageManager.PERMISSION_GRANTED){
                    saveImage();
                }else{
                    askPermisiion();
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK){
            if (requestCode == CAMERA_REQUEST_CDE){
                Bitmap imgResult = (Bitmap) data.getExtras().get("data");
                imgCaptureResult.setImageBitmap(imgResult);
                btnSvimg.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_STORAGE_REQUEST_CODE){
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                saveImage();

            }else{
                Toast.makeText(this, "Harapkan ijinkan untuk menyimpan gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private  void askPermisiion(){
        ActivityCompat.requestPermissions (this, new String[]
                {Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_STORAGE_REQUEST_CODE);
    }

    private void saveImage(){
        File dir = new File(Environment.getExternalStorageDirectory(), "Mobile_App");
        if(!dir.exists()){
            dir.mkdir();
        }
        BitmapDrawable drawable = (BitmapDrawable) imgCaptureResult.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        String imageName = System.currentTimeMillis()+".jpg";
        File file = new File(dir, imageName);
        try {
            outputStream = new FileOutputStream(file);
        }catch (FileNotFoundException e) {
            e.printStackTrace();

        }
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        Toast.makeText(this, "Gambar Berhasil Disimpan!!! :" + imageName, Toast.LENGTH_SHORT).show();

        try {
            outputStream.flush();
        }catch (IOException e) {
            e.printStackTrace();
        }
        try {
            outputStream.close();
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
}