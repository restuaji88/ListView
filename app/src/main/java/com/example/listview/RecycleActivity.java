package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class RecycleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MahasiswaAdapter adapter;
    private ArrayList<Mahasiswa> mahasiswaArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);

        addData();
        recyclerView = findViewById(R.id.recycle_view);
        adapter = new MahasiswaAdapter(mahasiswaArrayList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void addData() {
        mahasiswaArrayList = new ArrayList<>();
        mahasiswaArrayList.add(new Mahasiswa("Aditya Ibrar Abdillah", "E41210172", "085230258986"));
        mahasiswaArrayList.add(new Mahasiswa("Amelia Fawwaz Zain", "E41211978", "085226285326"));
        mahasiswaArrayList.add(new Mahasiswa("Deo Andreas Syaputra", "E41212022", "085572820852"));
        mahasiswaArrayList.add(new Mahasiswa("Muhammad Rayhan", "E41211999", "085486119856"));
        mahasiswaArrayList.add(new Mahasiswa("Restu Aji Prasetyo Saputra", "E41210002", "085238458192"));
        mahasiswaArrayList.add(new Mahasiswa("Aditya Ibrar Abdillah", "E41210172", "085230258986"));
        mahasiswaArrayList.add(new Mahasiswa("Amelia Fawwaz Zain", "E41211978", "085226285326"));
        mahasiswaArrayList.add(new Mahasiswa("Deo Andreas Syaputra", "E41212022", "085572820852"));
        mahasiswaArrayList.add(new Mahasiswa("Muhammad Rayhan", "E41211999", "085486119856"));
        mahasiswaArrayList.add(new Mahasiswa("Restu Aji Prasetyo Saputra", "E41210002", "085238458192"));
    }
}