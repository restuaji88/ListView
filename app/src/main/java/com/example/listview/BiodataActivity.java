package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class BiodataActivity extends AppCompatActivity implements View.OnClickListener{

    DatePickerDialog picker;
    EditText Nama, Alamat, Tanggal;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_biodata);

        Nama = findViewById(R.id.txt_name);
        Alamat = findViewById(R.id.txt_alamat);
        Tanggal = findViewById(R.id.txt_date);
        submit = findViewById(R.id.btn_submit);
        submit.setOnClickListener(this);
        Tanggal = (EditText) findViewById(R.id.txt_date);
        Tanggal.setInputType(InputType.TYPE_NULL);
        Tanggal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(BiodataActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int MonthOfYear, int DayOfMonth) {
                                Tanggal.setText(DayOfMonth + "/" + (MonthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (v == submit) {
            String nama = Nama.getText().toString();
            String alamat = Alamat.getText().toString();
            String tanggal = Tanggal.getText().toString();
            String message = "Nama: " + nama + "Alamat: " + alamat + "Tanggal Lahir: " + tanggal;
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        }
    }
}