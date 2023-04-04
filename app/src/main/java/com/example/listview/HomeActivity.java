package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    Button reg, lv, bo, fg , rc, sv, cam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        reg = findViewById(R.id.btn_reg);
        reg.setOnClickListener(this);
        lv = findViewById(R.id.btn_lv);
        lv.setOnClickListener(this);
        bo = findViewById(R.id.btn_bo);
        bo.setOnClickListener(this);
        fg = findViewById(R.id.btn_fg);
        fg.setOnClickListener(this);
        rc = findViewById(R.id.btn_rc);
        rc.setOnClickListener(this);
        sv = findViewById(R.id.btn_vs);
        sv.setOnClickListener(this);
        cam = findViewById(R.id.btn_camera);
        cam.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_reg:
                startActivity(new Intent(this, RegistrasiActivity.class));
                break;
            case R.id.btn_lv:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.btn_bo:
                startActivity(new Intent(this, BiodataActivity.class));
                break;
            case R.id.btn_fg:
                startActivity(new Intent(this, FragmentActivity.class));
                break;
            case R.id.btn_rc:
                startActivity(new Intent(this, RecycleActivity.class));
                break;
            case R.id.btn_vs:
                startActivity(new Intent(this, ImpIntentActivity.class));
                break;
            case R.id.btn_camera:
                startActivity(new Intent(this, CameraActivity.class));
                break;


        }
    }
}