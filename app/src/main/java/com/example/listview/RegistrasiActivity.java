package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrasiActivity extends AppCompatActivity implements View.OnClickListener {
    EditText nama, email, pass;
    Spinner spn;
    Button tombol;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        nama = findViewById(R.id.txt_nama);
        email = findViewById(R.id.txt_email);
        pass = findViewById(R.id.txt_pass);
        spn = findViewById(R.id.spn_jk);
        tombol = findViewById(R.id.btn_submit);
        tombol.setOnClickListener(this);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.kelamin, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v == tombol){
            String name = nama.getText().toString();
            String emel = email.getText().toString();
            String password = pass.getText().toString();
            String spinr = spn.getSelectedItem().toString();
            String hasil = "Nama: " + name + "\n" + "Email: " + emel + "\n" + "password: " + password + "\n" + "jenis kelamain" + spinr;
            Toast.makeText(this, hasil, Toast.LENGTH_SHORT).show();
        }
    }
}